package com.example.server.services;

import com.example.server.dto.SensorDto;
import com.example.server.mappers.SensorMapper;
import com.example.server.repositories.SensorRepository;
import com.example.server.util.exceptions.SensorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;

    public SensorDto findById(Long id) {
        return sensorMapper.toSensorDto(sensorRepository.findById(id).orElseThrow(SensorNotFoundException::new));
    }

    @Transactional
    public void save(SensorDto sensorDto) {
        sensorRepository.save(sensorMapper.toSensor(sensorDto));
    }
}
