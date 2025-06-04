package br.com.fiap.FarmaNear_Finder.domain;

public class Patient {

    private final long id;
    private final String addressName;

    public Patient(long id, String addressName) {
        this.id = id;
        this.addressName = addressName;
    }

    public String getAddressName() {
        return addressName;
    }
}
