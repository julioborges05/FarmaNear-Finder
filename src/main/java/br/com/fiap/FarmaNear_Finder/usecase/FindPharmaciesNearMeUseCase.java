package br.com.fiap.FarmaNear_Finder.usecase;

import br.com.fiap.FarmaNear_Finder.domain.Location;
import br.com.fiap.FarmaNear_Finder.domain.Patient;
import br.com.fiap.FarmaNear_Finder.domain.Pharmacy;
import org.springframework.stereotype.Service;

@Service
public class FindPharmaciesNearMeUseCase {

    private final PatientFinderUseCase patientFinderUseCase;
    private final GetLocationByAddressUseCase getLocationByAddressUseCase;

    public FindPharmaciesNearMeUseCase(PatientFinderUseCase patientFinderUseCase,
                                       GetLocationByAddressUseCase getLocationByAddressUseCase) {
        this.patientFinderUseCase = patientFinderUseCase;
        this.getLocationByAddressUseCase = getLocationByAddressUseCase;
    }

    public Pharmacy findPharmaciesNearMe(Long patientId) {
        Patient patient = patientFinderUseCase.findByPatientId(patientId);

        Location location = getLocationByAddressUseCase.getLocationByAddress(patient.getAddressName());

        // TODO: Based on the location, find pharmacies near the patient
        return null;
    }
}
