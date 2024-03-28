package com.example.server.controllers;

import com.example.server.dto.MeasurementDto;
import com.example.server.services.MeasurementService;
import com.example.server.util.ErrorEntity;
import com.example.server.util.MeasurementDtoValidator;
import com.example.server.util.exceptions.MeasurementNotCreatedException;
import com.example.server.util.exceptions.MeasurementNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final MeasurementDtoValidator measurementDtoValidator;

    @GetMapping
    public List<MeasurementDto> getAllMeasurements() {
        return measurementService.findAll();
    }

    @GetMapping("/{id}")
    public MeasurementDto getMeasurementById(@PathVariable Long id) {
        return measurementService.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createMeasurement(@RequestBody @Valid MeasurementDto measurementDto,
                                                        BindingResult bindingResult) {
        measurementDtoValidator.validate(measurementDto, bindingResult);
        if (bindingResult.hasErrors()) {
            String errorMessage = ErrorEntity.buildErrorMessage(bindingResult);
            throw new MeasurementNotCreatedException(errorMessage);
        }

        measurementService.save(measurementDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return measurementService.getRainyDaysCount();
    }

    @ExceptionHandler
    private ResponseEntity<ErrorEntity> handleException(MeasurementNotFoundException e) {
         ErrorEntity errorEntity = new ErrorEntity("Measurement with this id wasn't found!");
         return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorEntity> handleException(MeasurementNotCreatedException e) {
        ErrorEntity errorEntity = new ErrorEntity(e.getMessage());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }
}
