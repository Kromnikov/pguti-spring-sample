package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars", uniqueConstraints={
        @UniqueConstraint(columnNames = {"color", "model"})
})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "model", nullable = false)
    private String model;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "carId", referencedColumnName = "id")
    private List<Driver> drivers;
}
