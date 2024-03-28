package com.example.server.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SensorDto {
    @NotEmpty(message = "Name should be empty!")
    @Length(min = 3, max = 30, message = "Name length should be between 3 and 30 characters!")
    private String name;
}
