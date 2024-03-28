package com.example.server.repositories;

import com.example.server.models.Measurement;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    @EntityGraph(attributePaths = "sensor")
    List<Measurement> findAll();

    Long countByRaining(Boolean raining);
}
