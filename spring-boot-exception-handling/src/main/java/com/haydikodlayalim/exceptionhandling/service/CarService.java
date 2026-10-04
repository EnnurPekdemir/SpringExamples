package com.haydikodlayalim.exceptionhandling.service;

import com.haydikodlayalim.exceptionhandling.dto.Car;
import com.haydikodlayalim.exceptionhandling.exception.EntityNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private final List<Car> carList = new ArrayList<>();

    @PostConstruct
    public void init() {
        carList.add(Car.builder().id("1").name("M3").brand("BMW").build());
        carList.add(Car.builder().id("2").name("Civic").brand("Honda").build());
        carList.add(Car.builder().id("3").name("Corolla").brand("Toyota").build());
    }

    public Car getCar(String name) {
        return carList.stream()
                .filter(car -> car.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Arac bulunamadi: " + name));
    }
}
