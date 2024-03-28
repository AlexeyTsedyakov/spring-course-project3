package com.example.server.controllers;

import com.example.server.dto.SensorDto;
import com.example.server.services.SensorService;
import com.example.server.util.SensorDtoValidator;
import com.example.server.util.ErrorEntity;
import com.example.server.util.exceptions.SensorNotCreatedException;
import com.example.server.util.exceptions.SensorNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final SensorDtoValidator sensorDtoValidator;

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createSensor(@RequestBody @Valid SensorDto sensorDto,
                                                   BindingResult bindingResult) {
        sensorDtoValidator.validate(sensorDto, bindingResult);
        if (bindingResult.hasErrors()) {
            String message = ErrorEntity.buildErrorMessage(bindingResult);
            throw new SensorNotCreatedException(message);
        }

        sensorService.save(sensorDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorEntity> handleException(SensorNotFoundException e) {
        ErrorEntity errorEntity = new ErrorEntity("Sensor with this id wasn't found!");
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorEntity> handleException(SensorNotCreatedException e) {
        ErrorEntity errorEntity = new ErrorEntity(e.getMessage());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }
}
