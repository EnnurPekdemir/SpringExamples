package com.HaydiKodlayalim.controller;

import java.util.List;
import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.HaydiKodlayalim.dto.KisiDto;
import com.HaydiKodlayalim.service.KisiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/kisiler")
@RequiredArgsConstructor
public class KisiController {

    private final KisiService kisiService;

    @PostMapping
    public ResponseEntity<KisiDto> kaydet(@RequestBody KisiDto kisiDto){
        return ResponseEntity.ok(kisiService.save(kisiDto));
    }
    
    @GetMapping
    public ResponseEntity<List<KisiDto>> getAll(){
        return ResponseEntity.ok(kisiService.getAll());
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<KisiDto>> getAll(Pageable pageable){
        return ResponseEntity.ok(kisiService.getAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        kisiService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
