package br.com.fiap.FarmaNear_Finder.controller.dto;

import br.com.fiap.FarmaNear_Finder.model.Location;

public record LocationDto(double lat, double lng) {

  public LocationDto(Location location) {
    this(location.getLat(), location.getLng());
  }
}
