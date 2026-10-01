package com.HaydiKodlayalim.graphql.controller;

import com.HaydiKodlayalim.graphql.dto.VehicleDto;
import com.HaydiKodlayalim.graphql.entity.Vehicle;
import com.HaydiKodlayalim.graphql.repo.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    @QueryMapping
    public List<Vehicle> getVehicles(@Argument String type) {
        if (type != null && !type.isBlank()) {
            return vehicleRepository.getByTypeLike("%" + type + "%");
        }
        return vehicleRepository.findAll();
    }

    @QueryMapping
    public Optional<Vehicle> getById(@Argument Long id) {
        return vehicleRepository.findById(id);
    }

    @MutationMapping
    public Vehicle createVehicle(@Argument VehicleDto vehicle) {
        Vehicle vehicleEntity = Vehicle.builder()
                .type(vehicle.getType())
                .modelCode(vehicle.getModelCode())
                .brandName(vehicle.getBrandName())
                .launchDate(vehicle.getLaunchDate())
                .build();
        return vehicleRepository.save(vehicleEntity);
    }

    @MutationMapping
    public Boolean deleteVehicle(@Argument Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
