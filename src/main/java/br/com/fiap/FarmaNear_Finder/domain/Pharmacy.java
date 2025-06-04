package br.com.fiap.FarmaNear_Finder.domain;

import br.com.fiap.FarmaNear_Finder.controller.dto.PharmacyDto;

public class Pharmacy {

    private final String name;
    private final String addressName;

    public Pharmacy(String name, String addressName) {
        this.name = name;
        this.addressName = addressName;
    }

    public PharmacyDto convertToDto() {
        return new PharmacyDto(name, addressName);
    }

}
