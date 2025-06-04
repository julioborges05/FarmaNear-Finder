package br.com.fiap.FarmaNear_Finder.gateway.adapters.patient.api;

import br.com.fiap.FarmaNear_Finder.gateway.adapters.patient.api.fallback.PatientServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "PatientService",
        url = "${patient.service.url}",
        fallback = PatientServiceFallback.class
)
public interface PatientService {
}
