package br.com.fiap.FarmaNear_Finder.client.pharmacy.api;

import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.dto.GetDrugstoreByProductDto;
import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.fallback.PharmacyClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "PharmacyService",
        url = "${pharmacy.service.url}",
        fallback = PharmacyClientFallback.class
)
public interface PharmacyClient {

    @GetMapping("/product")
    ResponseEntity<GetDrugstoreByProductDto> getDrugstoreByProduct(@RequestParam String productName);

}
