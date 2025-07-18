package br.com.fiap.FarmaNear_Finder.client.pharmacy.api.dto;

import java.util.List;

public record GetDrugstoreByProductDto(String productName, List<PharmacyDto> drugstores) {
}
