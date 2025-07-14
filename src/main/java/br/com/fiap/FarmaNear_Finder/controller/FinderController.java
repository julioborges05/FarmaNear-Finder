package br.com.fiap.FarmaNear_Finder.controller;

import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.controller.dto.PharmacyDto;
import br.com.fiap.FarmaNear_Finder.model.Location;
import br.com.fiap.FarmaNear_Finder.model.Pharmacy;
import br.com.fiap.FarmaNear_Finder.service.FindPharmaciesNearService;
import br.com.fiap.FarmaNear_Finder.service.GetLocationByAddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finder")
public class FinderController {

  private final FindPharmaciesNearService findPharmaciesNearService;
  private final GetLocationByAddressService getLocationByAddressService;

  public FinderController(FindPharmaciesNearService findPharmaciesNearService,
                          GetLocationByAddressService getLocationByAddressService) {
    this.findPharmaciesNearService = findPharmaciesNearService;
    this.getLocationByAddressService = getLocationByAddressService;
  }

  @GetMapping("/pharmaciesNearMe/{patientId}")
  public List<PharmacyDto> findPharmacyNearMe(@PathVariable Long patientId) {
    List<Pharmacy> pharmaciesEntity = findPharmaciesNearService.findPharmaciesNearMe(patientId);

    return pharmaciesEntity.stream().map(PharmacyDto::new).toList();
  }

  @GetMapping("/pharmaciesNearCoordinates")
  public List<PharmacyDto> findPharmacyNearCoordinates(@ModelAttribute LocationDto locationDto) {
    List<Pharmacy> pharmaciesEntity = findPharmaciesNearService.findPharmaciesNearCoordinates(locationDto);

    return pharmaciesEntity.stream().map(PharmacyDto::new).toList();
  }

  @GetMapping("/pharmaciesNearAddress/{address}")
  public List<PharmacyDto> findPharmacyNearAddress(@PathVariable String address) {
    List<Pharmacy> pharmaciesEntity = findPharmaciesNearService.findPharmaciesNearAddress(address);

    return pharmaciesEntity.stream().map(PharmacyDto::new).toList();
  }

  @GetMapping("/location/address/{address}")
  public LocationDto findLocation(@PathVariable String address) {
    Location location = getLocationByAddressService.getLocationByAddress(address);
    return new LocationDto(location);
  }

  @GetMapping("/location/medicine/{medicine}")
  public List<LocationDto> findMedicineLocations(@PathVariable String medicine) {
    return null;
  }

}
