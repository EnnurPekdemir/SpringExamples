package com.haydikodlayalim.versioning.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductV2 {
    private String id;
    private String name;
    private BigDecimal price;
    private String currency;
}
