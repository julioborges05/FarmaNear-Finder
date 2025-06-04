package br.com.fiap.FarmaNear_Finder.usecase;

import br.com.fiap.FarmaNear_Finder.domain.Location;
import br.com.fiap.FarmaNear_Finder.gateway.adapters.google.maps.api.GoogleApiAdapter;
import org.springframework.stereotype.Service;

@Service
public class GetLocationByAddressUseCase {

    private final GoogleApiAdapter mapsApi;

    public GetLocationByAddressUseCase(GoogleApiAdapter mapsApi) {
        this.mapsApi = mapsApi;
    }

    public Location getLocationByAddress(String address) {
        return mapsApi.getLocationByAddress(address);
    }

}
