package br.com.fiap.FarmaNear_Finder.controller;

import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.entities.LocationEntity;
import br.com.fiap.FarmaNear_Finder.usecases.GetLocationByAddressUseCase;

public class FindLocationController {

  private final GetLocationByAddressUseCase getLocationByAddressUseCase;

  public FindLocationController(GetLocationByAddressUseCase getLocationByAddressUseCase) {
    this.getLocationByAddressUseCase = getLocationByAddressUseCase;
  }

  public LocationDto findLocation(String address) {
    LocationEntity locationEntity = getLocationByAddressUseCase.getLocationByAddress(address);
    return new LocationDto(locationEntity);
  }
}
