package com.example.spring.controllers;

import com.example.spring.entity.Car;
import com.example.spring.entity.Driver;
import com.example.spring.entity.SearchRequest;
import com.example.spring.services.CarsService;
import com.example.spring.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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