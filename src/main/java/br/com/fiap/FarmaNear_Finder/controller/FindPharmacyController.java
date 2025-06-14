package br.com.fiap.FarmaNear_Finder.controller;

import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.controller.dto.PharmacyDto;
import br.com.fiap.FarmaNear_Finder.entities.PharmacyEntity;
import br.com.fiap.FarmaNear_Finder.usecases.FindPharmaciesNearUseCase;

import java.util.List;

public class FindPharmacyController {

  private final FindPharmaciesNearUseCase findPharmaciesNearUseCase;

  public FindPharmacyController(FindPharmaciesNearUseCase findPharmaciesNearUseCase) {
    this.findPharmaciesNearUseCase = findPharmaciesNearUseCase;
  }

  public List<PharmacyDto> findPharmaciesNearMe(Long patientId) {
    List<PharmacyEntity> pharmaciesEntity = findPharmaciesNearUseCase.findPharmaciesNearMe(patientId);

    return pharmaciesEntity.stream().map(PharmacyDto::new).toList();
  }

  public List<PharmacyDto> findPharmaciesNearCoordinates(LocationDto locationDto) {
    List<PharmacyEntity> pharmaciesEntity = findPharmaciesNearUseCase.findPharmaciesNearCoordinates(locationDto);

    return pharmaciesEntity.stream().map(PharmacyDto::new).toList();
  }

  public List<PharmacyDto> findPharmaciesNearAddress(String address) {
    List<PharmacyEntity> pharmaciesEntity = findPharmaciesNearUseCase.findPharmaciesNearAddress(address);

    return pharmaciesEntity.stream().map(PharmacyDto::new).toList();
  }

  public List<LocationDto> findMedicineLocations(String medicine) {
    return null;
  }
}
