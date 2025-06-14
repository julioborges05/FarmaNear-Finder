package br.com.fiap.FarmaNear_Finder.infra.springController;

import br.com.fiap.FarmaNear_Finder.controller.FindLocationController;
import br.com.fiap.FarmaNear_Finder.controller.FindPharmacyController;
import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.controller.dto.PharmacyDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finder")
public class FinderController {

  private final FindPharmacyController findPharmacyController;
  private final FindLocationController findLocationController;

  public FinderController(FindPharmacyController findPharmacyController,
                          FindLocationController findLocationController) {
    this.findPharmacyController = findPharmacyController;
    this.findLocationController = findLocationController;
  }

  @GetMapping("/pharmaciesNearMe/{patientId}")
  public List<PharmacyDto> findPharmacyNearMe(@PathVariable Long patientId) {
    return findPharmacyController.findPharmaciesNearMe(patientId);
  }

  @GetMapping("/pharmaciesNearCoordinates")
  public List<PharmacyDto> findPharmacyNearCoordinates(@ModelAttribute LocationDto locationDto) {
    return findPharmacyController.findPharmaciesNearCoordinates(locationDto);
  }

  @GetMapping("/pharmaciesNearAddress/{address}")
  public List<PharmacyDto> findPharmacyNearAddress(@PathVariable String address) {
    return findPharmacyController.findPharmaciesNearAddress(address);
  }

  @GetMapping("/location/address/{address}")
  public LocationDto findLocation(@PathVariable String address) {
    return findLocationController.findLocation(address);
  }

  @GetMapping("/location/medicine/{medicine}")
  public List<LocationDto> findMedicineLocations(@PathVariable String medicine) {
    return findPharmacyController.findMedicineLocations(medicine);
  }

}
