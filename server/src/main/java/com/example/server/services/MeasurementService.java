package com.example.server.services;

import com.example.server.dto.MeasurementDto;
import com.example.server.mappers.MeasurementMapper;
import com.example.server.models.Measurement;
import com.example.server.models.Sensor;
import com.example.server.repositories.MeasurementRepository;
import com.example.server.repositories.SensorRepository;
import com.example.server.util.exceptions.MeasurementNotFoundException;
import com.example.server.util.exceptions.SensorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;
    private final MeasurementMapper measurementMapper;

    public List<MeasurementDto> findAll() {
        return measurementMapper.toMeasurementDtos(measurementRepository.findAll());
    }

    public MeasurementDto findById(Long id) {
        return measurementMapper.toMeasurementDto(measurementRepository.findById(id).orElseThrow(MeasurementNotFoundException::new));
    }

    @Transactional
    public void save(MeasurementDto measurementDto) {
        Measurement measurement =  measurementMapper.toMeasurement(measurementDto);
        Sensor sensor = sensorRepository.findByName(measurement.getSensor().getName()).orElseThrow(SensorNotFoundException::new);
        measurement.setSensor(sensor);
        measurementRepository.save(measurement);
    }

    public Long getRainyDaysCount() {
        return measurementRepository.countByRaining(true);
    }
}
