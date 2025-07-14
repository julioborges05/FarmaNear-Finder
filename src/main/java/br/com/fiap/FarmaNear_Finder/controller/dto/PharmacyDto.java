package br.com.fiap.FarmaNear_Finder.controller.dto;

import br.com.fiap.FarmaNear_Finder.model.Pharmacy;

public record PharmacyDto(Long id, String name, String addressName, LocationDto location) {

  public PharmacyDto(Pharmacy pharmacy) {
    this(pharmacy.getId(), pharmacy.getName(), pharmacy.getAddressName(), new LocationDto(pharmacy.getLocationEntity()));
  }
}
