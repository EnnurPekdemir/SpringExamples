package com.HaydiKodlayalim;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HaydiKodlayalim.entity.Kullanici;
import com.HaydiKodlayalim.repository.KullaniciRepository;

@RestController
@RequestMapping("/kullanici")
public class KullaniciApi {

    private final KullaniciRepository kullaniciRepository;

    public KullaniciApi(KullaniciRepository kullaniciRepository) {
        this.kullaniciRepository = kullaniciRepository;
    }

    @PostMapping
    public ResponseEntity<Kullanici> ekle(@RequestBody Kullanici kullanici) {
        return ResponseEntity.ok(kullaniciRepository.save(kullanici));
    }

    @GetMapping
    public ResponseEntity<List<Kullanici>> tumunuListele() {
        return ResponseEntity.ok(kullaniciRepository.findAll());
    }

    @GetMapping("/manuel-ekle")
    public ResponseEntity<Kullanici> manuelEkle(
            @RequestParam(defaultValue = "Ahmet") String adi,
            @RequestParam(defaultValue = "Yılmaz") String soyadi) {
        Kullanici k = new Kullanici();
        k.setAdi(adi);
        k.setSoyadi(soyadi);
        return ResponseEntity.ok(kullaniciRepository.save(k));
    }
}
