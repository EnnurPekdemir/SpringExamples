package com.HaydiKodlayalim.api;

import java.util.Calendar;
import java.util.List;

import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HaydiKodlayalim.entity.Kisi;
import com.HaydiKodlayalim.repository.KisiRepository;

@RestController
@RequestMapping("/kisi")
public class KisiController {

    private final KisiRepository kisiRepository;

    public KisiController(KisiRepository kisiRepository) {
        this.kisiRepository = kisiRepository;
    }

    @PostConstruct
    public void init() {
        Kisi kisi = new Kisi();
        kisi.setAd("haydi");
        kisi.setSoyad("Kodlayalim");
        kisi.setAdres("test");
        kisi.setDogumTarihi(Calendar.getInstance().getTime());
        kisi.setId("K0001");
        kisiRepository.save(kisi);
    }

    @GetMapping("/{search}")
    public ResponseEntity<List<Kisi>> getKisi(@PathVariable String search) {
        List<Kisi> kisiler = kisiRepository.getByCustomQuery(search);
        return ResponseEntity.ok(kisiler);
    }
}
