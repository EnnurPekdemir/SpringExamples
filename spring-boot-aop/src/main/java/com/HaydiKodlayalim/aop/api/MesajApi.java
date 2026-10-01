package com.HaydiKodlayalim.aop.api;

import com.HaydiKodlayalim.aop.service.IkinciMesajService;
import com.HaydiKodlayalim.aop.service.MesajService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mesaj")
public class MesajApi {

    private final MesajService mesajService;
    private final IkinciMesajService ikinciMesajService;

    public MesajApi(MesajService mesajService, IkinciMesajService ikinciMesajService) {
        this.mesajService = mesajService;
        this.ikinciMesajService = ikinciMesajService;
    }

    @GetMapping
    public ResponseEntity<String> mesajVer(@RequestParam(value = "param", defaultValue = "test") String param) {
        ikinciMesajService.mesaj(param);
        return ResponseEntity.ok(mesajService.mesajVer(param));
    }
}
