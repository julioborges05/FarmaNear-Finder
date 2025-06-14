package br.com.fiap.FarmaNear_Finder.usecases;

import br.com.fiap.FarmaNear_Finder.entities.LocationEntity;
import br.com.fiap.FarmaNear_Finder.infra.gateway.adapters.google.maps.api.GoogleApiAdapter;
import org.springframework.stereotype.Service;

@Service
public class GetLocationByAddressUseCase {

    private final GoogleApiAdapter mapsApi;

    public GetLocationByAddressUseCase(GoogleApiAdapter mapsApi) {
        this.mapsApi = mapsApi;
    }

    public LocationEntity getLocationByAddress(String address) {
        return mapsApi.getLocationByAddress(address);
    }

}
