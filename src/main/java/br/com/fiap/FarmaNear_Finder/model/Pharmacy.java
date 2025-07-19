package br.com.fiap.FarmaNear_Finder.model;

import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import jakarta.persistence.*;
import org.springframework.data.geo.Point;

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

    public Point getLocation() {
        return new Point(lat, lng);
    }
}
