package br.com.fiap.FarmaNear_Finder.service;

import br.com.fiap.FarmaNear_Finder.model.Patient;
import br.com.fiap.FarmaNear_Finder.client.patient.api.PatientService;
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
