package br.com.fiap.FarmaNear_Finder.infra.gateway.adapters.pharmacy.api;

import br.com.fiap.FarmaNear_Finder.infra.gateway.adapters.pharmacy.api.fallback.PharmacyServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "PharmacyService",
        url = "${pharmacy.service.url}",
        fallback = PharmacyServiceFallback.class
)
public interface PharmacyService {
}
