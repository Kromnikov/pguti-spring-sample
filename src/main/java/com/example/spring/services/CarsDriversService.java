package com.example.spring.services;

import com.example.spring.entity.CarsDrivers;
import com.example.spring.entity.Driver;
import com.example.spring.repository.CarsDriversRepository;
import com.example.spring.repository.CarsRepository;
import com.example.spring.repository.DriversRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CarsDriversService {
    @Autowired
    private CarsDriversRepository carsDriversRepository;
    @Autowired
    private CarsRepository carsRepository;
    @Autowired
    private DriversRepository driversRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addDriversToCar(long carId, List<Long> driversIds) {
        if (!carsRepository.existsById(carId)) {
            throw new ResponseStatusException(NOT_FOUND, "Car with id '" + carId + "' not found");
        }

        driversIds.forEach(driverId -> {
            if (!driversRepository.existsById(driverId)) {
                throw new ResponseStatusException(NOT_FOUND, "Driver with id '" + driverId + "' not found");
            }
            carsDriversRepository.save(new CarsDrivers(carId, driverId));
        });
    }

    public List<Driver> getDriversByCar(long carId) {
        // 2 запроса в базу
        List<CarsDrivers> carsDrivers = carsDriversRepository.findCarsDriversByCarId(carId);
        List<Driver> drivers1 = driversRepository.findDriversByIdIn(
                carsDrivers.stream()
                        .map(CarsDrivers::getDriverId)
                        .collect(Collectors.toList())
        );

        // 1 запрос в базу
        List<Driver> drivers2 = driversRepository.findDriversByCarId(carId);

        // 1 запрос в базу
        List<Driver> drivers3 = new ArrayList<>();
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from drivers where id in (select driver_id from cars_drivers where car_id = ?)", carId);
        list.forEach(m -> {
            Driver driver = new Driver();
            driver.setId((Long)m.get("id"));
            driver.setFirstName((String)m.get("first_name"));
            driver.setSecondName((String)m.get("second_name"));
            drivers3.add(driver);
        });
        return drivers2;
    }
}
