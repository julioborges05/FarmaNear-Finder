package br.com.fiap.FarmaNear_Finder.client.pharmacy.api.fallback;

import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.PharmacyClient;
import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.dto.GetDrugstoreByProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PharmacyClientFallback implements PharmacyClient {
    @Override
    public ResponseEntity<GetDrugstoreByProductDto> getDrugstoreByProduct(String productName) {
        throw new RuntimeException("Pharmacy service is currently unavailable. Please try again later.");
    }
}
