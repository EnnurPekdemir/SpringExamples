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
        if (carRepository.count() < 500) {
            carRepository.deleteAll();
            String[] brands = {"BMW", "Mercedes", "Audi", "Toyota", "Honda", "Volkswagen", "Ford", "Volvo"};
            String[] models = {"Model X", "Model Y", "Sedan", "Hatchback", "SUV", "Coupe", "Sport", "Touring"};

            log.info("--> Test verileri veritabanina ekleniyor (500 adet arac)...");
            for (int i = 1; i <= 500; i++) {
                Car car = Car.builder()
                        .brand(brands[i % brands.length])
                        .model(models[i % models.length] + " #" + i)
                        .year(2000 + (i % 25))
                        .build();
                carRepository.save(car);
            }
            log.info("--> 500 adet ornek arac basariyla PostgreSQL veritabanina kaydedildi.");
        }
    }

    // 1. Ilk istekte gercek PostgreSQL'e gider, sonraki isteklerde doğrudan Hazelcast RAM onbelleginden doner
    @Cacheable(value = "cars-cache", key = "#id")
    public Car getCarById(Long id) {
        log.info("--> [POSTGRESQL DB SORGUSU] id: {} icin veritabanina gidiliyor...", id);
        return carRepository.findById(id)
                .orElse(Car.builder().id(id).model("Bilinmeyen").brand("Bilinmeyen").year(2000).build());
    }

    // 2. Yeni arac ekler ve ayni anda cache'e yazar
    @CachePut(value = "cars-cache", key = "#result.id")
    public Car saveCar(Car car) {
        log.info("--> [POSTGRESQL DB KAYIT] Yeni arac veritabanina kaydediliyor: {}", car.getModel());
        return carRepository.save(car);
    }

    // 3. Butun araclari listeler (Hazelcast cache uzerinden saklanir)
    @Cacheable(value = "all-cars-cache")
    public List<Car> getAllCars() {
        log.info("--> [POSTGRESQL DB SORGUSU] Tum araclar veritabanindan cekiliyor (findAll)...");
        return carRepository.findAll();
    }

    // 4. Cache'i temizler
    @CacheEvict(value = {"cars-cache", "all-cars-cache"}, allEntries = true)
    public String clearCache() {
        log.info("--> Hazelcast onbellekleri ('cars-cache', 'all-cars-cache') tamamen temizlendi!");
        return "Hazelcast onbellek basariyla temizlendi.";
    }
}
