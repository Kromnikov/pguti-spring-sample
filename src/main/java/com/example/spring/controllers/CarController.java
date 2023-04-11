package com.example.spring.controllers;

import com.example.spring.entity.Car;
import com.example.spring.entity.SearchRequest;
import com.example.spring.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CarController {

    @Autowired
    private CarsService carsService;

    @GetMapping(value = "/cars/{id}")
    public Car getCar(@PathVariable("id") long id) {
        return carsService.getCar(id);
    }

    @GetMapping(value = "/cars")
    public Iterable<Car> getAllCar() {
        return carsService.getAllCar();
    }

    @PostMapping("/cars")
    public Car createCar(@RequestBody Car car) {
        return carsService.createCar(car);
    }

    @PostMapping("/cars/find")
    public List<Car> findCars(@RequestBody SearchRequest request) {
        return carsService.findCars(request);
    }
}