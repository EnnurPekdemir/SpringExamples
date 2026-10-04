package com.haydikodlayalim.versioning.api;

import com.haydikodlayalim.versioning.dto.ProductV1;
import com.haydikodlayalim.versioning.dto.ProductV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/param")
public class ProductParamVersioningController {

    @GetMapping(value = "/product", params = "apiVersion=1")
    public ProductV1 getProductV1() {
        return ProductV1.builder()
                .id("1")
                .name("Keyboard")
                .build();
    }

    @GetMapping(value = "/product", params = "apiVersion=2")
    public ProductV2 getProductV2() {
        return ProductV2.builder()
                .id("1")
                .name("Keyboard")
                .price(BigDecimal.valueOf(1250.00))
                .currency("TRY")
                .build();
    }
}
