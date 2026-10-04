package com.haydikodlayalim.kafka.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/kmessage")
@RequiredArgsConstructor
public class ResourceController {

    @Value("${hk.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public String sendMessage(@RequestBody(required = false) String message) {
        try {
            String msgToSend = (message != null && !message.isBlank()) ? message : "Default Kafka Test Message";
            kafkaTemplate.send(topic, UUID.randomUUID().toString(), msgToSend);
            return "Mesaj Kafka'ya gonderildi: " + msgToSend;
        } catch (Exception e) {
            e.printStackTrace();
            return "Hata: " + e.getMessage();
        }
    }
}
