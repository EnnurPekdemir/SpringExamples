package com.haydikodlayalim.events.event.listener;

import com.haydikodlayalim.events.event.ReservationCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ReservationEventListener {

    @EventListener
    public void handleReservationCreatedEvent(ReservationCreatedEvent event) {
        System.out.println("[Senkron Listener] Rezervasyon oluşturuldu: " + event.getUserId() + " - Otel: " + event.getHotelName());
    }

    @Async
    @EventListener
    public void sendEmailNotification(ReservationCreatedEvent event) {
        try {
            Thread.sleep(3000L);
            System.out.println("[Asenkron Listener] E-posta gönderildi -> Kullanıcı: " + event.getUserId() + ", Otel: " + event.getHotelName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Async
    @EventListener
    public void sendSmsNotification(ReservationCreatedEvent event) {
        try {
            Thread.sleep(2000L);
            System.out.println("[Asenkron Listener] SMS gönderildi -> Kullanıcı: " + event.getUserId() + ", Otel: " + event.getHotelName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
