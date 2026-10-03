package com.haydikodlayalim.events.event;

import org.springframework.context.ApplicationEvent;

public class ReservationCreatedEvent extends ApplicationEvent {

    private final String userId;
    private final String hotelName;

    public ReservationCreatedEvent(Object source, String userId, String hotelName) {
        super(source);
        this.userId = userId;
        this.hotelName = hotelName;
    }

    public String getUserId() {
        return userId;
    }

    public String getHotelName() {
        return hotelName;
    }
}
