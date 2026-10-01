package com.HaydiKodlayalim;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Pet", description = "Benim Pet nesnem")
public class Pet {

    @Schema(description = "Benzersiz kimlik alanı", example = "1")
    private int id;

    @Schema(description = "Pet ismi", example = "Pamuk")
    private String name;

    @Schema(description = "Kayıt tarihi")
    private Date date;
}
