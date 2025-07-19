package br.com.fiap.FarmaNear_Finder.model;

import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.geo.Point;

@Entity
public class Pharmacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cnpj;
    private Point location;

    public Pharmacy() {
    }

    public Pharmacy(Long id, String cnpj, LocationDto locationDto) {
        this.id = id;
        this.cnpj = cnpj;
        this.location = new Point(locationDto.lat(), locationDto.lng());
    }

    public Pharmacy(String cnpj, LocationDto locationDto) {
        this.cnpj = cnpj;
        this.location = new Point(locationDto.lat(), locationDto.lng());
    }

    public Long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Point getLocation() {
        return location;
    }
}
