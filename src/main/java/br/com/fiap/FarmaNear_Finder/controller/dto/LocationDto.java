package br.com.fiap.FarmaNear_Finder.controller.dto;

import br.com.fiap.FarmaNear_Finder.entities.LocationEntity;

public record LocationDto(double lat, double lng) {

  public LocationDto(LocationEntity locationEntity) {
    this(locationEntity.getLat(), locationEntity.getLng());
  }
}
