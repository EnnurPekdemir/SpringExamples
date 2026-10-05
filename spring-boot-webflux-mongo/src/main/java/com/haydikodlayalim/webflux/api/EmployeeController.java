package com.haydikodlayalim.webflux.api;

import com.haydikodlayalim.webflux.entity.Employee;
import com.haydikodlayalim.webflux.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/{id}")
    public Mono<Employee> getById(@PathVariable String id) {
        return employeeRepository.findById(id);
    }

    @GetMapping
    public Flux<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @PostMapping
    public Mono<Employee> save(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
}
