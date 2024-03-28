package org.example.client;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Measurement {
    private BigDecimal value;
    private Boolean raining;
    private Sensor sensor;
}
