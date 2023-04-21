package com.example.spring.repository;

import com.example.spring.entity.CarsDrivers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarsDriversRepository extends CrudRepository<CarsDrivers, Long> {
    List<CarsDrivers> findCarsDriversByCarId(long carId);
}
