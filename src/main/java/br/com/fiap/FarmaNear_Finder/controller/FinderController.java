package br.com.fiap.FarmaNear_Finder.controller;

import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.domain.Location;
import br.com.fiap.FarmaNear_Finder.usecase.GetLocationByAddressUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class FinderController {

    private final GetLocationByAddressUseCase getLocationByAddressUseCase;

    @Autowired
    public FinderController(GetLocationByAddressUseCase getLocationByAddressUseCase) {
        this.getLocationByAddressUseCase = getLocationByAddressUseCase;
    }

    @GetMapping
    public LocationDto findLocation() {
        Location location = getLocationByAddressUseCase.getLocationByAddress("Avenida Paulista, São Paulo, Brasil");
        return location.convertToDto();
    }

}
