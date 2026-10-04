package com.haydikodlayalim.versioning.api;

import com.haydikodlayalim.versioning.dto.ProductV1;
import com.haydikodlayalim.versioning.dto.ProductV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class ProductPathVersioningController {

    @GetMapping("/v1/product")
    public ProductV1 getProductV1() {
        return ProductV1.builder()
                .id("1")
                .name("Laptop")
                .build();
    }

    @GetMapping("/v2/product")
    public ProductV2 getProductV2() {
        return ProductV2.builder()
                .id("1")
                .name("Laptop")
                .price(BigDecimal.valueOf(25000.00))
                .currency("TRY")
                .build();
    }
}
