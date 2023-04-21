package com.example.spring.controllers;

import com.example.spring.annotations.CurrentUser;
import com.example.spring.entity.Car;
import com.example.spring.entity.Driver;
import com.example.spring.entity.SearchRequest;
import com.example.spring.services.CarsDriversService;
import com.example.spring.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CarController {

    @Autowired
    private CarsService carsService;

    @Autowired
    private CarsDriversService carsDriversService;

    @GetMapping(value = "/cars/{id}")
    public Car getCar(@PathVariable("id") long id, @CurrentUser String user) {
        System.out.println(user);
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

    @PostMapping("/cars/{id}/drivers")
    public void addDriversToCar(@PathVariable("id") long id, @RequestBody List<Long> driversIds) {
        carsDriversService.addDriversToCar(id, driversIds);
    }

    @GetMapping("/cars/{id}/drivers")
    public List<Driver> getDriversByCar(@PathVariable("id") long id) {
        return carsDriversService.getDriversByCar(id);
    }
}