package br.com.fiap.FarmaNear_Finder.controller;

import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.service.GoogleLocationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/finder")
public class FinderController {

    private final GoogleLocationService googleLocationService;

    public FinderController(GoogleLocationService googleLocationService) {
        this.googleLocationService = googleLocationService;
    }

    @GetMapping("/location/address/{address}")
    public LocationDto findLocation(@PathVariable String address) {
        return googleLocationService.getLocationByAddress(address);
    }

}
