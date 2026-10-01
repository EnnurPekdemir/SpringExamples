package com.HaydiKodlayalim.sr.producer;

import com.HaydiKodlayalim.sr.model.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class NotificationProducer {

    @Value("${sr.rabbit.exchange.name}")
    private String exchangeName;

    @Value("${sr.rabbit.routing.name}")
    private String routingName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Notification notification = new Notification();
        notification.setNotificationId(UUID.randomUUID().toString());
        notification.setMessage("Hello from RabbitMQ!");
        notification.setCreatedAt(new Date());
        notification.setSeen(false);

        sendToQueue(notification);
    }
    public void sendToQueue(Notification notification) {
        System.out.println("Notification sent ID: " + notification.getNotificationId());
        rabbitTemplate.convertAndSend(exchangeName, routingName, notification);
    }
}
