package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cars_drivers")
@NoArgsConstructor
public class CarsDrivers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "car_id", nullable = false)
    Long carId;

    @Column(name = "driver_id", nullable = false)
    Long driverId;

    public CarsDrivers(Long carId, Long driverId) {
        this.carId = carId;
        this.driverId = driverId;
    }
}