package com.HaydiKodlayalim;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pet")
@Tag(name = "Pet Controller", description = "Pet API işlemleri")
public class PetController {

    private final List<Pet> petList = new ArrayList<>();

    @PostConstruct
    public void init() {
        petList.add(new Pet(1, "Test Pet", new Date()));
    }

    @PostMapping
    @Operation(summary = "Yeni Pet Kaydet", description = "Bu metot yeni bir pet kaydı oluşturur.")
    public ResponseEntity<Pet> kaydet(@RequestBody @Parameter(description = "Kaydedilecek Pet nesnesi") Pet pet) {
        petList.add(pet);
        return ResponseEntity.ok(pet);
    }

    @GetMapping
    @Operation(summary = "Tüm Petleri Listele", description = "Sistemdeki tüm pet kayıtlarını döner.")
    public ResponseEntity<List<Pet>> tumunuListele() {
        return ResponseEntity.ok(petList);
    }
}
