package br.com.fiap.FarmaNear_Finder.controller;

import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.dto.PharmacyDto;
import br.com.fiap.FarmaNear_Finder.service.PharmacyService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class QueueReceiver {

    private final PharmacyService pharmacyService;

    public QueueReceiver(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

//    @KafkaListener(topics = "drugstore-data")
//    public void receive(PharmacyDto pharmacyCreated) {
//        pharmacyService.handleWithPharmacyCreatedEvent(pharmacyCreated);
//    }

}
