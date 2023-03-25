package com.example.spring.repository;

import com.example.spring.entity.Driver;
import org.springframework.data.repository.CrudRepository;

public interface DriversRepository extends CrudRepository<Driver, Long> {
}
