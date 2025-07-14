package br.com.fiap.FarmaNear_Finder.service;

import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.dto.PharmacyDto;
import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.model.Pharmacy;
import br.com.fiap.FarmaNear_Finder.repository.PharmacyRepository;
import org.springframework.stereotype.Service;

@Service
public class PharmacyService {

    private final GoogleLocationService googleLocationService;
    private final PharmacyRepository repository;

    public PharmacyService(GoogleLocationService googleLocationService, PharmacyRepository repository) {
        this.googleLocationService = googleLocationService;
        this.repository = repository;
    }

    public void handleWithPharmacyCreatedEvent(PharmacyDto pharmacyCreated) {
        String addressString = pharmacyCreated.address().street() + ", " +
                pharmacyCreated.address().number() + ", " +
                pharmacyCreated.address().complement() + ", " +
                pharmacyCreated.address().city() + ", " +
                pharmacyCreated.address().state() + ", " +
                pharmacyCreated.address().zipCode();

        LocationDto location = googleLocationService.getLocationByAddress(addressString);

        Pharmacy pharmacy = new Pharmacy(pharmacyCreated.cnpj(), location);
        repository.save(pharmacy);
    }
}
