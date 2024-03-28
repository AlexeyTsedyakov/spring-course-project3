package com.example.server.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sensor_id")
    private Long id;

    @Column(name = "name")
    private String name;
}
