package com.HaydiKodlayalim.api;
import com.HaydiKodlayalim.repository.KisiRepository;
import lombok.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/kisi")
public class KisiController {
    private final KisiRepository kisiRepository;
    @PostConstruct
    public void init(){
        Kisi kisi = new Kisi();
        kisi.setAd("haydi");
        kisi.setSoyad("Kodlayalim");
        kisi.setAdres("test";
        kisi.setDogumTarihi(Calendar.getInstance().getTime());
        kisi.setId("K0001");
        kisiRepository.save(kisi);
 }

 @GetMapping("/{search}")
    public ResponseEntity<List<Kisi>> getKisi(@PathVariable String search){
List<Kisi> kisiler= kisiRepository.getByCustomQuery(search);
return ResponseEntity.ok(kisiler);

  }
}
