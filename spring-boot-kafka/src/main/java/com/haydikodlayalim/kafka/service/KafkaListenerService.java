package com.haydikodlayalim.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaListenerService {

    @KafkaListener(
            topics = "${hk.kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void listen(String message) {
        log.info("Kafka'dan yeni mesaj geldi: {}", message);
    }
}
