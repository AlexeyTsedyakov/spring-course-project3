package com.example.server.util;

import com.example.server.dto.MeasurementDto;
import com.example.server.repositories.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class MeasurementDtoValidator implements Validator {
    private final SensorRepository sensorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(MeasurementDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public void validate(Object target, Errors errors) {
        MeasurementDto measurementDto = (MeasurementDto) target;
        if (sensorRepository.findByName(measurementDto.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "", "Sensor with this name does not exist!");
        }
    }
}
