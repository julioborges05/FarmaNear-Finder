package br.com.fiap.FarmaNear_Finder.service;

import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.dto.PharmacyDto;
import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.client.google.maps.api.GoogleApiAdapter;
import br.com.fiap.FarmaNear_Finder.model.Pharmacy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    public Map<String, LocationDto> findPharmaciesByProductAndAddress(String productName, String address) {
        List<PharmacyDto> pharmacies = pharmacyService.findPharmacyCnpjWithProduct(productName);
        List<String> cnpjs = extractCnpjsFromPharmacies(pharmacies);
        LocationDto location = getLocationByAddress(address);

        List<Pharmacy> selectedPharmacies = pharmacyService.findPharmaciesByCnpjNearLocation(cnpjs, location);

        return mapPharmaciesToLocations(pharmacies, selectedPharmacies);
    }

    private List<String> extractCnpjsFromPharmacies(List<PharmacyDto> pharmacies) {
        return pharmacies.stream()
                .map(PharmacyDto::cnpj)
                .toList();
    }

    private Map<String, LocationDto> mapPharmaciesToLocations(List<PharmacyDto> pharmacies, List<Pharmacy> nearbyPharmacies) {
        Map<String, LocationDto> pharmacyMap = new HashMap<>();

        Map<String, PharmacyDto> pharmacyDtoMap = pharmacies.stream()
                .collect(Collectors.toMap(PharmacyDto::cnpj, pharmacyDto -> pharmacyDto));

        for (Pharmacy pharmacy : nearbyPharmacies) {
            PharmacyDto pharmacyDto = pharmacyDtoMap.get(pharmacy.getCnpj());
            if (pharmacyDto != null) {
                pharmacyMap.put(pharmacyDto.name(), new LocationDto(pharmacy.getLat(), pharmacy.getLng()));
            }
        }

        return pharmacyMap;
    }
}
