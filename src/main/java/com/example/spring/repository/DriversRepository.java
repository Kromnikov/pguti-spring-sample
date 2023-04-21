package com.example.spring.repository;

import com.example.spring.entity.Driver;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriversRepository extends CrudRepository<Driver, Long> {
    String FIND_BY_CARS_DRIVERS_TABLE = "select * from drivers where id in (select driver_id from cars_drivers where car_id = :id)";

    List<Driver> findDriversByIdIn(List<Long> ids);

    @Query(value = FIND_BY_CARS_DRIVERS_TABLE, nativeQuery = true)
    List<Driver> findDriversByCarId(@Param("id") Long id);
}
