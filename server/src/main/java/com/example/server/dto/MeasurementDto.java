package com.example.server.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MeasurementDto {
    @NotNull
    @Range(min = -100, max = 100, message = "Value should be between -100 and 100!")
    private BigDecimal value;

    @NotNull(message = "Raining should not be empty!")
    private Boolean raining;

    @NotNull(message = "Sensor should not be empty!")
    private SensorDto sensor;

    private LocalDateTime created_at;
}
