package br.com.fiap.FarmaNear_Finder.repository;

import br.com.fiap.FarmaNear_Finder.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    List<Pharmacy> findAllByCnpjInAndNearByLatAndLng(List<String> cnpjList, double lat, double lng);
}
