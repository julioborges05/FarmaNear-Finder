package br.com.fiap.FarmaNear_Finder.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class GoogleMapsService {

    @Value("${maps.google.apikey}")
    private String apiKey;

    private final GeoApiContext context = new GeoApiContext.Builder()
            .apiKey(apiKey)
            .build();

    public LatLng getLatLngFromAddress(String address) throws InterruptedException, ApiException, IOException {
        GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
        if (Objects.nonNull(results) && results.length > 0) {
            return results[0].geometry.location;
        }
        return null;
    }

}
