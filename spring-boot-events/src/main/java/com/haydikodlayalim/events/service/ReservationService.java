package com.haydikodlayalim.events.service;

import com.haydikodlayalim.events.api.HotelBookRequest;
import com.haydikodlayalim.events.event.ReservationCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ApplicationEventPublisher eventPublisher;

    public void publishReservationEvent(HotelBookRequest hotelBookRequest) {
        ReservationCreatedEvent event = new ReservationCreatedEvent(
                this,
                hotelBookRequest.getUserId(),
                hotelBookRequest.getHotelName());
        eventPublisher.publishEvent(event);

        System.out.println("[Service] Event fırlatıldı ve servis akışına devam etti.");
    }
}
