package br.com.fiap.FarmaNear_Finder.controller.dto;

import br.com.fiap.FarmaNear_Finder.entities.PharmacyEntity;

public record PharmacyDto(Long id, String name, String addressName, LocationDto location) {

  public PharmacyDto(PharmacyEntity pharmacyEntity) {
    this(pharmacyEntity.getId(), pharmacyEntity.getName(), pharmacyEntity.getAddressName(), new LocationDto(pharmacyEntity.getLocationEntity()));
  }
}
