package com.example.server.util;

import com.example.server.dto.SensorDto;
import com.example.server.repositories.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class SensorDtoValidator implements Validator {
    private final SensorRepository sensorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SensorDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public void validate(Object target, Errors errors) {
        SensorDto sensorDto = (SensorDto) target;
        if (sensorRepository.findByName(sensorDto.getName()).isPresent()) {
            errors.rejectValue("name", "", "Sensor with this name already exist!");
        }
    }
}
