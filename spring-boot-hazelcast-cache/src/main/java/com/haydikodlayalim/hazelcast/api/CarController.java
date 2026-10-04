package com.haydikodlayalim.hazelcast.api;

import com.haydikodlayalim.hazelcast.dto.Car;
import com.haydikodlayalim.hazelcast.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @PostMapping
    public Car saveCar(@RequestBody Car car) {
        return carService.saveCar(car);
    }

    @DeleteMapping("/cache")
    public String clearCache() {
        return carService.clearCache();
    }
}
