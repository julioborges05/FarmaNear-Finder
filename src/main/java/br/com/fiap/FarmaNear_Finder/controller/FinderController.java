package br.com.fiap.FarmaNear_Finder.controller;

import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/finder")
public class FinderController {

    private final LocationService locationService;

    public FinderController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/location/address/{address}")
    public LocationDto findLocation(@PathVariable String address) {
        return locationService.getLocationByAddress(address);
    }

    @GetMapping
    public Map<String, LocationDto> findPharmaciesByProductAndAddress(@RequestParam String productName,
                                                                      @RequestParam String address) {
        return locationService.findPharmaciesByProductAndAddress(productName, address);
    }

}
