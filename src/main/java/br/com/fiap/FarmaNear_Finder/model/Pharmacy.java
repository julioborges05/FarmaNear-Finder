package br.com.fiap.FarmaNear_Finder.model;

import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pharmacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cnpj;
    private double lat;
    private double lng;

    public Pharmacy() {
    }

    public Pharmacy(Long id, String cnpj, LocationDto locationDto) {
        this.id = id;
        this.cnpj = cnpj;
        this.lat = locationDto.lat();
        this.lng = locationDto.lng();
    }

    public Pharmacy(String cnpj, LocationDto locationDto) {
        this.cnpj = cnpj;
        this.lat = locationDto.lat();
        this.lng = locationDto.lng();
    }

    public Long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
