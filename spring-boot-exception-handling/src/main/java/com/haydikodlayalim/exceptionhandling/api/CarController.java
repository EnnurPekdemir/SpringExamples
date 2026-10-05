package com.haydikodlayalim.exceptionhandling.api;

import com.haydikodlayalim.exceptionhandling.dto.Car;
import com.haydikodlayalim.exceptionhandling.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public Car getCar(@RequestParam String name) {
        return carService.getCar(name);
    }
}
