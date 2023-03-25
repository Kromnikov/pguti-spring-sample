package com.example.spring.services;

import com.example.spring.entity.Driver;
import com.example.spring.repository.DriversRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    @Autowired
    private DriversRepository driversRepository;

    public Iterable<Driver> getAllDrivers() {
        return driversRepository.findAll();
    }

    public Driver createDriver(Driver car) {
        return driversRepository.save(car);
    }
}
