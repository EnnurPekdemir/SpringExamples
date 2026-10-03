package com.haydikodlayalim.events.api;

import com.haydikodlayalim.events.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<String> bookHotel(@RequestBody HotelBookRequest hotelBookRequest) {
        reservationService.publishReservationEvent(hotelBookRequest);
        return ResponseEntity.ok("Rezervasyon talebi alındı: " + hotelBookRequest.getUserId());
    }
}
