package br.com.fiap.FarmaNear_Finder.service;

import br.com.fiap.FarmaNear_Finder.model.Location;
import br.com.fiap.FarmaNear_Finder.client.google.maps.api.GoogleApiAdapter;
import org.springframework.stereotype.Service;

@Service
public class GetLocationByAddressService {

    private final GoogleApiAdapter mapsApi;

    public GetLocationByAddressService(GoogleApiAdapter mapsApi) {
        this.mapsApi = mapsApi;
    }

    public Location getLocationByAddress(String address) {
        return mapsApi.getLocationByAddress(address);
    }

}
