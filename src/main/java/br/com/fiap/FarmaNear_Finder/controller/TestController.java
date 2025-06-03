package br.com.fiap.FarmaNear_Finder.controller;

import br.com.fiap.FarmaNear_Finder.service.GoogleMapsService;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestController {

    private final GoogleMapsService googleMapsService;

    @Autowired
    public TestController(GoogleMapsService googleMapsService) {
        this.googleMapsService = googleMapsService;
    }

    @GetMapping
    public LatLng test() throws IOException, InterruptedException, ApiException {
        return googleMapsService.getLatLngFromAddress("Avenida Paulista, São Paulo, Brasil");
    }

}
