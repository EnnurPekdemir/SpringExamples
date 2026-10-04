package com.haydikodlayalim.versioning.api;

import com.haydikodlayalim.versioning.dto.ProductV1;
import com.haydikodlayalim.versioning.dto.ProductV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/media-type")
public class ProductMediaTypeVersioningController {

    @GetMapping(value = "/product", produces = "application/vnd.company.app-v1+json")
    public ProductV1 getProductV1() {
        return ProductV1.builder()
                .id("1")
                .name("Monitor")
                .build();
    }

    @GetMapping(value = "/product", produces = "application/vnd.company.app-v2+json")
    public ProductV2 getProductV2() {
        return ProductV2.builder()
                .id("1")
                .name("Monitor")
                .price(BigDecimal.valueOf(4500.00))
                .currency("TRY")
                .build();
    }
}
