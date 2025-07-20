package br.com.fiap.FarmaNear_Finder.service;

import br.com.fiap.FarmaNear_Finder.client.google.maps.api.GoogleApiAdapter;
import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.dto.PharmacyDto;
import br.com.fiap.FarmaNear_Finder.controller.dto.FinderAddressReceiver;
import br.com.fiap.FarmaNear_Finder.controller.dto.FinderCoordinatesReceiver;
import br.com.fiap.FarmaNear_Finder.controller.dto.FinderResponse;
import br.com.fiap.FarmaNear_Finder.controller.dto.LocationDto;
import br.com.fiap.FarmaNear_Finder.model.Pharmacy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LocationServiceTest {

    @Mock
    private GoogleApiAdapter googleApiAdapter;

    @Mock
    private PharmacyService pharmacyService;

    @InjectMocks
    private LocationService locationService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testGetLocationByAddress() {
        String address = "123 Main St";
        LocationDto expectedLocation = new LocationDto(40.7128, -74.0060);

        when(googleApiAdapter.getLocationByAddress(address)).thenReturn(expectedLocation);

        LocationDto result = locationService.getLocationByAddress(address);

        assertEquals(expectedLocation, result);
        verify(googleApiAdapter, times(1)).getLocationByAddress(address);
    }

    @Test
    void testFindPharmaciesByProductAndAddress() {
        FinderAddressReceiver receiver = new FinderAddressReceiver("123 Main St", "Aspirin", 10);
        LocationDto location = new LocationDto(40.7128, -74.0060);
        List<PharmacyDto> pharmacyDtos = List.of(new PharmacyDto("123456789", "Pharmacy A", "", "", null));
        List<Pharmacy> pharmacies = List.of(new Pharmacy("123456789", location));

        when(pharmacyService.findPharmacyCnpjWithProduct(receiver.medicine())).thenReturn(pharmacyDtos);
        when(googleApiAdapter.getLocationByAddress(receiver.address())).thenReturn(location);
        when(pharmacyService.findPharmaciesByCnpjNearLocation(anyList(), eq(location), eq(receiver.radius()))).thenReturn(pharmacies);

        List<FinderResponse> result = locationService.findPharmaciesByProductAndAddress(receiver);

        assertEquals(1, result.size());
        assertEquals("123456789", result.get(0).id());
        assertEquals("Pharmacy A", result.get(0).name());
        verify(pharmacyService, times(1)).findPharmacyCnpjWithProduct(receiver.medicine());
        verify(googleApiAdapter, times(1)).getLocationByAddress(receiver.address());
        verify(pharmacyService, times(1)).findPharmaciesByCnpjNearLocation(anyList(), eq(location), eq(receiver.radius()));
    }

    @Test
    void testFindPharmaciesByProductAndCoordinates() {
        FinderCoordinatesReceiver receiver = new FinderCoordinatesReceiver("Aspirin", new LocationDto(40.7128, -74.0060), 10);
        LocationDto location = new LocationDto(40.7128, -74.0060);
        List<PharmacyDto> pharmacyDtos = List.of(new PharmacyDto("123456789", "Pharmacy A", "", "", null));
        List<Pharmacy> pharmacies = List.of(new Pharmacy("123456789", location));

        when(pharmacyService.findPharmacyCnpjWithProduct(receiver.medicine())).thenReturn(pharmacyDtos);
        when(pharmacyService.findPharmaciesByCnpjNearLocation(anyList(), eq(location), eq(receiver.radius()))).thenReturn(pharmacies);

        List<FinderResponse> result = locationService.findPharmaciesByProductAndCoordinates(receiver);

        assertEquals(1, result.size());
        assertEquals("123456789", result.get(0).id());
        assertEquals("Pharmacy A", result.get(0).name());
        verify(pharmacyService, times(1)).findPharmacyCnpjWithProduct(receiver.medicine());
        verify(pharmacyService, times(1)).findPharmaciesByCnpjNearLocation(anyList(), eq(location), eq(receiver.radius()));
    }
}