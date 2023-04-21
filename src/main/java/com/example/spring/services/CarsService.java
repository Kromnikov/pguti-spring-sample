package com.example.spring.services;

import com.example.spring.entity.Car;
import com.example.spring.entity.SearchRequest;
import com.example.spring.repository.CarsRepository;
import com.example.spring.repository.DriversRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CarsService {

    @Autowired
    private CarsRepository carsRepository;
    @Autowired
    private DriversRepository driversRepository;

    public Car getCar(long id) {
        Car car = carsRepository.findById(id);
        if (car == null) {
            throw new ResponseStatusException(NOT_FOUND, "Car with id '" + id + "' not found");
        }
        return car;
    }

    public Iterable<Car> getAllCar() {
        return carsRepository.findAll();
    }

    public Car createCar(Car car) {
        //TODO кейс с manytomany
//        car.getDrivers().forEach(driversRepository::save);
        return carsRepository.save(car);
    }

    public List<Car> findCars(@RequestBody SearchRequest request) {
        return carsRepository.findCarsByColorAndModel(request.getColor(), request.getModel());
    }
}
