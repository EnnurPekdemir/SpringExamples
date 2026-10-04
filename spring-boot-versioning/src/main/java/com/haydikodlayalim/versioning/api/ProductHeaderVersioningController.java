package com.haydikodlayalim.versioning.api;

import com.haydikodlayalim.versioning.dto.ProductV1;
import com.haydikodlayalim.versioning.dto.ProductV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
@RequestMapping("/api/header")
public class ProductHeaderVersioningController {

    @GetMapping(value = "/product", headers = "X-API-VERSION=1")
    public ProductV1 getProductV1() {
        return ProductV1.builder()
                .id("1")
                .name("Mouse")
                .build();
    }

    @GetMapping(value = "/product", headers = "X-API-VERSION=2")
    public ProductV2 getProductV2() {
        return ProductV2.builder()
                .id("1")
                .name("Mouse")
                .price(BigDecimal.valueOf(750.00))
                .currency("TRY")
                .build();
    }
}
