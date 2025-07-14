package br.com.fiap.FarmaNear_Finder.service;

import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.client.google.maps.api.GoogleApiAdapter;
import org.springframework.stereotype.Service;

@Service
public class GoogleLocationService {

    private final GoogleApiAdapter mapsApi;

    public GoogleLocationService(GoogleApiAdapter mapsApi) {
        this.mapsApi = mapsApi;
    }

    public LocationDto getLocationByAddress(String address) {
        return mapsApi.getLocationByAddress(address);
    }

}
