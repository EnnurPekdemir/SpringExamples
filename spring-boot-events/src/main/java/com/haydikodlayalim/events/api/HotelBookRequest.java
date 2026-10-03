package com.haydikodlayalim.events.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HotelBookRequest {
    private String userId;
    private String hotelName;
}
