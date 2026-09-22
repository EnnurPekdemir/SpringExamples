package com.HaydiKodlayalim.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KisiDto {

    private Long id;
    private String ad;
    private String soyad;
    private List<String> adresler;
}
