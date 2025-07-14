package br.com.fiap.FarmaNear_Finder.client.google.maps.api;

import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GoogleApiAdapter {

    @Value("${maps.google.apikey}")
    private String apiKey;

    private final GeoApiContext context = new GeoApiContext.Builder()
            .apiKey(apiKey)
            .build();

    public LocationDto getLocationByAddress(String address) {
        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, address).await();

            validateApiResult(results);
            return convertToLocation(results[0]);
        } catch (IOException | InterruptedException | ApiException exception) {
            throw new RuntimeException("Error while fetching location for address: " + address, exception);
        }
    }

    private void validateApiResult(GeocodingResult[] results) {
        if (results == null || results.length == 0) {
            throw new RuntimeException("No results found for address");
        }
    }

    private LocationDto convertToLocation(GeocodingResult result) {
        LatLng location = result.geometry.location;
        return new LocationDto(location.lat, location.lng);
    }

}
