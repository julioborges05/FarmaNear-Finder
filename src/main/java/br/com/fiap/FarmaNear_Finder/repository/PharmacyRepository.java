package br.com.fiap.FarmaNear_Finder.repository;

import br.com.fiap.FarmaNear_Finder.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

    @Query(value = """
            SELECT p.*
            FROM pharmacy p
            WHERE 
                p.cnpj IN (:cnpjList)
                AND
                (
                  6371 * acos(
                    cos(radians(:lat)) * cos(radians(p.lat)) *
                    cos(radians(p.lng) - radians(:lng)) +
                    sin(radians(:lat)) * sin(radians(p.lat))
                  )
                ) <= :radiusInKm
            """, nativeQuery = true)
    List<Pharmacy> findAllByCnpjInAndNearByPoint(List<String> cnpjList, double lat, double lng, int radiusInKm);

}
