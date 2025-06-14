package br.com.fiap.FarmaNear_Finder.usecases;

import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.entities.LocationEntity;
import br.com.fiap.FarmaNear_Finder.entities.PatientEntity;
import br.com.fiap.FarmaNear_Finder.entities.PharmacyEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindPharmaciesNearUseCase {

  private final PatientFinderUseCase patientFinderUseCase;
  private final GetLocationByAddressUseCase getLocationByAddressUseCase;

  public FindPharmaciesNearUseCase(PatientFinderUseCase patientFinderUseCase,
                                   GetLocationByAddressUseCase getLocationByAddressUseCase) {
    this.patientFinderUseCase = patientFinderUseCase;
    this.getLocationByAddressUseCase = getLocationByAddressUseCase;
  }

  public List<PharmacyEntity> findPharmaciesNearMe(Long patientId) {
    PatientEntity patientEntity = patientFinderUseCase.findByPatientId(patientId);
    LocationEntity locationEntity = getLocationByAddressUseCase.getLocationByAddress(patientEntity.getAddressName());

    // TODO: Based on the location, find pharmacies near the patient
    return null;
  }

  public List<PharmacyEntity> findPharmaciesNearCoordinates(LocationDto locationDto) {
    LocationEntity locationEntity = new LocationEntity(locationDto.lat(), locationDto.lng());

    // TODO: Based on the location, find pharmacies near the coordinates
    return null;
  }

  public List<PharmacyEntity> findPharmaciesNearAddress(String address) {
    LocationEntity locationEntity = getLocationByAddressUseCase.getLocationByAddress(address);

    // TODO: Based on the address, find pharmacies near the address
    return null;
  }
}
