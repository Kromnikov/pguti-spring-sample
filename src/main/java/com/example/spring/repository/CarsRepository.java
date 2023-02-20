package com.example.spring.repository;

import com.example.spring.entity.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarsRepository extends CrudRepository<Car, Long> {
    Car findById(long id);

    List<Car> findCarsByColor(String color);

    List<Car> findCarsByColorAndModel(String color, String model);
}
