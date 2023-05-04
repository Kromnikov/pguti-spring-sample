package com.example.spring.controllers;

import com.example.spring.entity.Driver;
import com.example.spring.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriversController {

    @Autowired
    private DriverService driverService;

    @GetMapping(value = "/drivers")
    public Iterable<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @PostMapping("/drivers")
    public Driver createCar(@RequestBody Driver car) {
        return driverService.createDriver(car);
    }
}