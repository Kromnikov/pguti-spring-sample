package com.example.spring.controllers;

import com.example.spring.annotations.CurrentUser;
import com.example.spring.entity.Car;
import com.example.spring.entity.Driver;
import com.example.spring.entity.SearchRequest;
import com.example.spring.services.CarsDriversService;
import com.example.spring.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarsService carsService;

    @Autowired
    private CarsDriversService carsDriversService;

    @GetMapping(value = "/{id}")
    public Car getCar(@PathVariable("id") long id, @CurrentUser String user) {
        // Just example for @CurrentUser
        System.out.println(user);
        return carsService.getCar(id);
    }

    @GetMapping()
    public Iterable<Car> getAllCar() {
        return carsService.getAllCar();
    }

    @PostMapping()
    public Car createCar(@RequestBody Car car) {
        return carsService.createCar(car);
    }

    // Only for demo - hasAnyRole('ROLE_ROLE2') return 403!
    @PreAuthorize("hasAnyRole('ROLE_ROLE2') or hasAuthority('ROLE1_READ')")
    @PostMapping("/find")
    public List<Car> findCars(@RequestBody SearchRequest request) {
        return carsService.findCars(request);
    }

    @PostMapping("/{id}/drivers")
    public void addDriversToCar(@PathVariable("id") long id, @RequestBody List<Long> driversIds) {
        carsDriversService.addDriversToCar(id, driversIds);
    }

    @GetMapping("/{id}/drivers")
    public List<Driver> getDriversByCar(@PathVariable("id") long id) {
        return carsDriversService.getDriversByCar(id);
    }
}