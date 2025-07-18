package br.com.fiap.FarmaNear_Finder.service;

import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.PharmacyClient;
import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.dto.GetDrugstoreByProductDto;
import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.dto.PharmacyDto;
import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.model.Pharmacy;
import br.com.fiap.FarmaNear_Finder.repository.PharmacyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PharmacyService {

    private final LocationService locationService;
    private final PharmacyRepository repository;
    private final PharmacyClient client;

    public PharmacyService(LocationService locationService, PharmacyRepository repository, PharmacyClient client) {
        this.locationService = locationService;
        this.repository = repository;
        this.client = client;
    }

    public void handleWithPharmacyCreatedEvent(PharmacyDto pharmacyCreated) {
        String addressString = pharmacyCreated.address().street() + ", " +
                pharmacyCreated.address().number() + ", " +
                pharmacyCreated.address().complement() + ", " +
                pharmacyCreated.address().city() + ", " +
                pharmacyCreated.address().state() + ", " +
                pharmacyCreated.address().zipCode();

        LocationDto location = locationService.getLocationByAddress(addressString);

        Pharmacy pharmacy = new Pharmacy(pharmacyCreated.cnpj(), location);
        repository.save(pharmacy);
    }

    public List<PharmacyDto> findPharmacyCnpjWithProduct(String productName) {
        GetDrugstoreByProductDto pharmacyDto = client.getDrugstoreByProduct(productName).getBody();

        return Objects.requireNonNull(pharmacyDto).drugstores();
    }

    public List<Pharmacy> findPharmaciesByCnpjNearLocation(List<String> cnpjList, LocationDto locationDto) {
        return repository.findAllByCnpjInAndNearByLatAndLng(cnpjList, locationDto.lat(), locationDto.lng());
    }
}
