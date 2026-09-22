package com.HaydiKodlayalim.service.Impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HaydiKodlayalim.dto.KisiDto;
import com.HaydiKodlayalim.entity.Adres;
import com.HaydiKodlayalim.entity.Kisi;
import com.HaydiKodlayalim.repository.AdresRepository;
import com.HaydiKodlayalim.repository.KisiRepository;
import com.HaydiKodlayalim.service.KisiService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KisiServiceImpl implements KisiService {

    private final KisiRepository kisiRepository; 
    private final AdresRepository adresRepository;

    @Override 
    @Transactional
    public KisiDto save(KisiDto kisiDto) {
        Kisi kisi = new Kisi();
        kisi.setAd(kisiDto.getAd());
        kisi.setSoyad(kisiDto.getSoyad());
        final Kisi kisiDb = kisiRepository.save(kisi);

        List<Adres> liste = new ArrayList<>();
        if (kisiDto.getAdresler() != null) {
            kisiDto.getAdresler().forEach(item -> {
                Adres adres = new Adres();
                adres.setAdres(item);
                adres.setAdresTip(Adres.AdresTip.DIGER);
                adres.setAktif(true);
                adres.setKisi(kisiDb);
                liste.add(adres);
            });
            adresRepository.saveAll(liste);
        }
        kisiDto.setId(kisiDb.getId());
        return kisiDto;    
    }

    @Override 
    @Transactional
    public void delete(Long id) {
        kisiRepository.deleteById(id);
    }

    @Override 
    @Transactional(readOnly = true)
    public List<KisiDto> getAll() {
        List<Kisi> kisiler = kisiRepository.findAll();
        List<KisiDto> kisiDtos = new ArrayList<>();

        kisiler.forEach(it -> {
            KisiDto kisiDto = new KisiDto();
            kisiDto.setId(it.getId());
            kisiDto.setAd(it.getAd());
            kisiDto.setSoyad(it.getSoyad());
            kisiDto.setAdresler(it.getAdresler() != null ? 
                it.getAdresler().stream().map(Adres::getAdres).toList() : null);
            kisiDtos.add(kisiDto);
        });
        return kisiDtos;    
    }

    @Override 
    @Transactional(readOnly = true)
    public Page<KisiDto> getAll(Pageable pageable) {
        Page<Kisi> kisiler = kisiRepository.findAll(pageable);
        return kisiler.map(it -> {
            KisiDto kisiDto = new KisiDto();
            kisiDto.setId(it.getId());
            kisiDto.setAd(it.getAd());
            kisiDto.setSoyad(it.getSoyad());
            kisiDto.setAdresler(it.getAdresler() != null ? 
                it.getAdresler().stream().map(Adres::getAdres).toList() : null);
            return kisiDto;
        });
    }
}
