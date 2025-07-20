package br.com.fiap.FarmaNear_Finder.service;

import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.dto.PharmacyDto;
import br.com.fiap.FarmaNear_Finder.controller.dto.FinderAddressReceiver;
import br.com.fiap.FarmaNear_Finder.controller.dto.FinderCoordinatesReceiver;
import br.com.fiap.FarmaNear_Finder.controller.dto.FinderResponse;
import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.client.google.maps.api.GoogleApiAdapter;
import br.com.fiap.FarmaNear_Finder.model.Pharmacy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final GoogleApiAdapter mapsApi;
    private final PharmacyService pharmacyService;

    public LocationService(GoogleApiAdapter mapsApi, PharmacyService pharmacyService) {
        this.mapsApi = mapsApi;
        this.pharmacyService = pharmacyService;
    }

    public LocationDto getLocationByAddress(String address) {
        return mapsApi.getLocationByAddress(address);
    }

    public List<FinderResponse> findPharmaciesByProductAndAddress(FinderAddressReceiver finderReceiver) {
        List<PharmacyDto> pharmacies = pharmacyService.findPharmacyCnpjWithProduct(finderReceiver.medicine());
        List<String> cnpjs = extractCnpjsFromPharmacies(pharmacies);
        LocationDto location = getLocationByAddress(finderReceiver.address());

        List<Pharmacy> selectedPharmacies = pharmacyService.findPharmaciesByCnpjNearLocation(cnpjs, location, finderReceiver.radius());

        return mapPharmaciesToLocations(pharmacies, selectedPharmacies);
    }

    public List<FinderResponse> findPharmaciesByProductAndCoordinates(FinderCoordinatesReceiver finderReceiver) {
        List<PharmacyDto> pharmacies = pharmacyService.findPharmacyCnpjWithProduct(finderReceiver.medicine());
        List<String> cnpjs = extractCnpjsFromPharmacies(pharmacies);
        LocationDto location = new LocationDto(finderReceiver.coordinates().lat(), finderReceiver.coordinates().lng());

        List<Pharmacy> selectedPharmacies = pharmacyService.findPharmaciesByCnpjNearLocation(cnpjs, location, finderReceiver.radius());

        return mapPharmaciesToLocations(pharmacies, selectedPharmacies);
    }

    private List<String> extractCnpjsFromPharmacies(List<PharmacyDto> pharmacies) {
        return pharmacies.stream()
                .map(PharmacyDto::cnpj)
                .toList();
    }

    private List<FinderResponse> mapPharmaciesToLocations(List<PharmacyDto> pharmacies, List<Pharmacy> nearbyPharmacies) {
        List<FinderResponse> responseList = new ArrayList<>();

        Map<String, PharmacyDto> pharmacyDtoMap = pharmacies.stream()
                .collect(Collectors.toMap(PharmacyDto::cnpj, pharmacyDto -> pharmacyDto));

        for (Pharmacy pharmacy : nearbyPharmacies) {
            PharmacyDto pharmacyDto = pharmacyDtoMap.get(pharmacy.getCnpj());
            if (pharmacyDto == null) continue;

            LocationDto location = new LocationDto(pharmacy.getLocation().getX(), pharmacy.getLocation().getY());
            responseList.add(new FinderResponse(pharmacyDto.cnpj(), pharmacyDto.name(), location));
        }

        return responseList;
    }
}
