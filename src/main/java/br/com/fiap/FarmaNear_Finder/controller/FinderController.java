package br.com.fiap.FarmaNear_Finder.controller;

import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.controller.dto.PharmacyDto;
import br.com.fiap.FarmaNear_Finder.domain.Location;
import br.com.fiap.FarmaNear_Finder.domain.Pharmacy;
import br.com.fiap.FarmaNear_Finder.usecase.FindPharmaciesNearMeUseCase;
import br.com.fiap.FarmaNear_Finder.usecase.GetLocationByAddressUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finder")
public class FinderController {

    private final GetLocationByAddressUseCase getLocationByAddressUseCase;
    private final FindPharmaciesNearMeUseCase findPharmaciesNearMeUseCase;

    public FinderController(GetLocationByAddressUseCase getLocationByAddressUseCase,
                            FindPharmaciesNearMeUseCase findPharmaciesNearMeUseCase) {
        this.getLocationByAddressUseCase = getLocationByAddressUseCase;
        this.findPharmaciesNearMeUseCase = findPharmaciesNearMeUseCase;
    }

    @GetMapping("/findPharmacyNearMe/{patientId}")
    public PharmacyDto findPharmacyNearMe(Long patientId) {
        Pharmacy pharmacy = findPharmaciesNearMeUseCase.findPharmaciesNearMe(patientId);

        return pharmacy.convertToDto();
    }

    @GetMapping
    public LocationDto findLocation() {
        Location location = getLocationByAddressUseCase.getLocationByAddress("Avenida Paulista, São Paulo, Brasil");
        return location.convertToDto();
    }

}
