package br.com.fiap.FarmaNear_Finder.infra.config;

import br.com.fiap.FarmaNear_Finder.controller.FindLocationController;
import br.com.fiap.FarmaNear_Finder.controller.FindPharmacyController;
import br.com.fiap.FarmaNear_Finder.usecases.FindPharmaciesNearUseCase;
import br.com.fiap.FarmaNear_Finder.usecases.GetLocationByAddressUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinderConfig {

  @Bean
  FindLocationController findLocationController(GetLocationByAddressUseCase getLocationByAddressUseCase) {
    return new FindLocationController(getLocationByAddressUseCase);
  }

  @Bean
  FindPharmacyController findPharmacyController(FindPharmaciesNearUseCase findPharmaciesNearUseCase) {
    return new FindPharmacyController(findPharmaciesNearUseCase);
  }
}
