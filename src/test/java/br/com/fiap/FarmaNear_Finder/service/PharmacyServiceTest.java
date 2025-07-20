package br.com.fiap.FarmaNear_Finder.service;

import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.PharmacyClient;
import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.dto.GetDrugstoreByProductDto;
import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.dto.PharmacyAddressDto;
import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.dto.PharmacyDto;
import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.dto.PharmacyQueueReceiverDto;
import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.model.Pharmacy;
import br.com.fiap.FarmaNear_Finder.repository.PharmacyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PharmacyServiceTest {

    @InjectMocks
    private PharmacyService pharmacyService;

    @Mock
    private LocationService locationService;

    @Mock
    private PharmacyRepository repository;

    @Mock
    private PharmacyClient client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleWithPharmacyCreatedEvent() {
        PharmacyAddressDto addressDto = new PharmacyAddressDto(
                "Main St",
                "123",
                "Suite 100",
                "New York",
                "NY",
                "10001"
        );

        PharmacyQueueReceiverDto event = new PharmacyQueueReceiverDto(
                "123456789",
                addressDto
        );
        LocationDto location = new LocationDto(40.7128, -74.0060);
        Pharmacy pharmacy = new Pharmacy("123456789", location);

        when(locationService.getLocationByAddress(anyString())).thenReturn(location);

        pharmacyService.handleWithPharmacyCreatedEvent(event);

        verify(repository, times(1)).save(refEq(pharmacy));
    }

    @Test
    void testFindPharmacyCnpjWithProduct() {
        String productName = "Aspirin";
        PharmacyDto pharmacyDto = new PharmacyDto("123456789", "Pharmacy A", "", "", null);
        GetDrugstoreByProductDto response = new GetDrugstoreByProductDto("Pharmacy A", List.of(pharmacyDto));

        when(client.getDrugstoreByProduct(productName)).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        List<PharmacyDto> result = pharmacyService.findPharmacyCnpjWithProduct(productName);

        assertEquals(1, result.size());
        assertEquals("123456789", result.get(0).cnpj());
        verify(client, times(1)).getDrugstoreByProduct(productName);
    }

    @Test
    void testFindPharmaciesByCnpjNearLocation() {
        List<String> cnpjList = List.of("123456789");
        LocationDto location = new LocationDto(40.7128, -74.0060);
        Pharmacy pharmacy = new Pharmacy("123456789", location);

        when(repository.findAllByCnpjInAndNearByPoint(cnpjList, location.lat(), location.lng(), 10))
                .thenReturn(List.of(pharmacy));

        List<Pharmacy> result = pharmacyService.findPharmaciesByCnpjNearLocation(cnpjList, location, 10);

        assertEquals(1, result.size());
        assertEquals("123456789", result.get(0).getCnpj());
        verify(repository, times(1)).findAllByCnpjInAndNearByPoint(cnpjList, location.lat(), location.lng(), 10);
    }
}