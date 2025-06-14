package br.com.fiap.FarmaNear_Finder.usecases;

import br.com.fiap.FarmaNear_Finder.entities.PatientEntity;
import br.com.fiap.FarmaNear_Finder.infra.gateway.adapters.patient.api.PatientService;
import org.springframework.stereotype.Service;

@Service
public class PatientFinderUseCase {

  private final PatientService patientService;

  public PatientFinderUseCase(PatientService patientService) {
    this.patientService = patientService;
  }

  public PatientEntity findByPatientId(Long patientId) {
    // TODO: Call the patient service to retrieve patient information
    return null;
  }

}
