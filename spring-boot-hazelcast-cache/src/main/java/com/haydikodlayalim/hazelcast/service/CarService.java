package com.haydikodlayalim.hazelcast.service;

import com.haydikodlayalim.hazelcast.dto.Car;
import com.haydikodlayalim.hazelcast.repo.CarRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    @PostConstruct
    public void init() {
        if (carRepository.count() == 0) {
            carRepository.save(Car.builder().model("M3").brand("BMW").year(2023).build());
            carRepository.save(Car.builder().model("Civic").brand("Honda").year(2022).build());
            carRepository.save(Car.builder().model("Corolla").brand("Toyota").year(2024).build());
            log.info("--> PostgreSQL veritabanina ornek araclar eklendi.");
        }
    }

    // 1. Ilk istekte gercek PostgreSQL'e gider, sonraki isteklerde doğrudan Hazelcast RAM onbelleginden doner
    @Cacheable(value = "cars-cache", key = "#id")
    public Car getCarById(Long id) {
        log.info("--> [POSTGRESQL DB SORGUSU] id: {} icin veritabanina gidiliyor...", id);
        simulateSlowDatabaseCall();
        return carRepository.findById(id)
                .orElse(Car.builder().id(id).model("Bilinmeyen").brand("Bilinmeyen").year(2000).build());
    }

    // 2. Yeni arac ekler ve ayni anda cache'e yazar
    @CachePut(value = "cars-cache", key = "#result.id")
    public Car saveCar(Car car) {
        log.info("--> [POSTGRESQL DB KAYIT] Yeni arac veritabanina kaydediliyor: {}", car.getModel());
        return carRepository.save(car);
    }

    // 3. Butun araclari listeler
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // 4. Cache'i temizler
    @CacheEvict(value = "cars-cache", allEntries = true)
    public String clearCache() {
        log.info("--> Hazelcast 'cars-cache' tamamen temizlendi!");
        return "Hazelcast onbellek basariyla temizlendi.";
    }

    private void simulateSlowDatabaseCall() {
        try {
            Thread.sleep(1500); // 1.5 saniye DB gecikmesi simülasyonu
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
