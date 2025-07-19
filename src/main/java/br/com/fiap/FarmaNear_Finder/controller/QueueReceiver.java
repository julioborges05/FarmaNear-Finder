package br.com.fiap.FarmaNear_Finder.controller;

import br.com.fiap.FarmaNear_Finder.client.pharmacy.api.dto.PharmacyQueueReceiverDto;
import br.com.fiap.FarmaNear_Finder.service.PharmacyService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class QueueReceiver {

    private final PharmacyService pharmacyService;

    public QueueReceiver(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @KafkaListener(topics = "drugstore-data", groupId = "drugstore-group")
    public void receive(PharmacyQueueReceiverDto pharmacyCreated) {
        pharmacyService.handleWithPharmacyCreatedEvent(pharmacyCreated);
    }

}
