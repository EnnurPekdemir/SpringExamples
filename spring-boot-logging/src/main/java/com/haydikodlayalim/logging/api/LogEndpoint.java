package com.haydikodlayalim.logging.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogEndpoint {


    @GetMapping
    public String getDetails() {
        log.debug("getLog metodu çağrıldı.");
        return internalLogDetail();
    }
    /*loglama asenktron olmalı
    printstracketrace ve system.out.println kullanılmamalıdır.
    sensitive data olmamalıdır
    tüm loglar merkezi toplanmalıdır belirli bir formatta
    level dikkatli kullanılmalı
    farklı leveller için farkli appenderlar kullanılmalı
    
     */
private String internalLogDetail(){
    try{
        log.debug("internalLogDetail metodu çağrıldı.");
        Thread.sleep(1000);
        return "API Mesaj";
    }catch(InterruptedException e){
        log.error("Hata : {}",e);
    }
    return  "";
}

}
