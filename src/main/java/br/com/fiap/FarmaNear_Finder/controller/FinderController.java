package br.com.fiap.FarmaNear_Finder.controller;

import br.com.fiap.FarmaNear_Finder.controller.dto.FinderAddressReceiver;
import br.com.fiap.FarmaNear_Finder.controller.dto.FinderCoordinatesReceiver;
import br.com.fiap.FarmaNear_Finder.controller.dto.FinderResponse;
import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/local/medicine/address")
    public List<FinderResponse> findPharmaciesByProductAndAddress(@RequestBody FinderAddressReceiver finderReceiver) {
        return locationService.findPharmaciesByProductAndAddress(finderReceiver);
    }

    @PostMapping("/local/medicine/coordinates")
    public List<FinderResponse> findPharmaciesByProductAndAddress(@RequestBody FinderCoordinatesReceiver finderReceiver) {
        return locationService.findPharmaciesByProductAndCoordinates(finderReceiver);
    }

}
