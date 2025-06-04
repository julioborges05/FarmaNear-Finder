package br.com.fiap.FarmaNear_Finder.usecase;

import br.com.fiap.FarmaNear_Finder.domain.Patient;
import br.com.fiap.FarmaNear_Finder.gateway.adapters.patient.api.PatientService;
import org.springframework.stereotype.Service;

@Service
public class PatientFinderUseCase {

    private final PatientService patientService;

    public PatientFinderUseCase(PatientService patientService) {
        this.patientService = patientService;
    }

    public Patient findByPatientId(Long patientId) {
        // TODO: Call the patient service to retrieve patient information
        return null;
    }

}
