package br.com.fiap.FarmaNear_Finder.domain;

import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;

public class Location {

    private final double lat;
    private final double lng;

    public Location(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public LocationDto convertToDto() {
        return new LocationDto(lat, lng);
    }
}
