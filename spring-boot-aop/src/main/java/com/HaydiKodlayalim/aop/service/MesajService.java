package com.HaydiKodlayalim.aop.service;

import org.springframework.stereotype.Service;

@Service
public class MesajService {

    public String mesajVer(String param) {
        System.out.println("-> Metot calisti. Parametre: " + param);
        if ("hata".equalsIgnoreCase(param)) {
            throw new IllegalArgumentException("Hata parametresi gönderildi!");
        }
        return "Mesaj: " + param;
    }
}
