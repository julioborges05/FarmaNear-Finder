package br.com.fiap.FarmaNear_Finder.service;

import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.model.Location;
import br.com.fiap.FarmaNear_Finder.model.Patient;
import br.com.fiap.FarmaNear_Finder.model.Pharmacy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindPharmaciesNearService {

    private final PatientFinderUseCase patientFinderUseCase;
    private final GetLocationByAddressService getLocationByAddressService;

    @Autowired
    public FindPharmaciesNearService(PatientFinderUseCase patientFinderUseCase,
                                     GetLocationByAddressService getLocationByAddressService) {
        this.patientFinderUseCase = patientFinderUseCase;
        this.getLocationByAddressService = getLocationByAddressService;
    }

    public List<Pharmacy> findPharmaciesNearMe(Long patientId) {
        Patient patient = patientFinderUseCase.findByPatientId(patientId);
        Location location = getLocationByAddressService.getLocationByAddress(patient.getAddressName());

        // TODO: Based on the location, find pharmacies near the patient
        return null;
    }

    public List<Pharmacy> findPharmaciesNearCoordinates(LocationDto locationEntity) {

        // TODO: Based on the location, find pharmacies near the coordinates
        return null;
    }

    public List<Pharmacy> findPharmaciesNearAddress(String address) {
        Location location = getLocationByAddressService.getLocationByAddress(address);

        // TODO: Based on the address, find pharmacies near the address
        return null;
    }
}
