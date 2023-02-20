package com.example.spring.services;

import com.example.spring.entity.Car;
import com.example.spring.entity.SearchRequest;
import com.example.spring.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CarsService {

    @Autowired
    private CarsRepository carsRepository;

    public Car getCar(long id) {
        return carsRepository.findById(id);
    }

    public Iterable<Car> getAllCar() {
        return carsRepository.findAll();
    }

    public Car createCar(Car car) {
        return carsRepository.save(car);
    }

    public List<Car> findCars(@RequestBody SearchRequest request) {
        return carsRepository.findCarsByColorAndModel(request.getColor(), request.getModel());
    }
}
