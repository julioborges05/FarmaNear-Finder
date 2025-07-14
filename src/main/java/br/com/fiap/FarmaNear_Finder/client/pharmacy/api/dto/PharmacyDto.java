package br.com.fiap.FarmaNear_Finder.client.pharmacy.api.dto;

public record PharmacyDto(String cnpj, String name, String email, String phone, PharmacyAddressDto address) {
}
