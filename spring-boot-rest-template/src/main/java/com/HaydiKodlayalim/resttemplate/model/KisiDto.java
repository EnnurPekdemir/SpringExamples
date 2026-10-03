package com.HaydiKodlayalim.resttemplate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KisiDto implements Serializable {

    private Long id;
    private String ad;
    private String soyad;
    private List<String> adresler;
}
