package org.example.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Measurement {
    private BigDecimal value;
    private Boolean raining;
    private Sensor sensor;
    private LocalDateTime created_at;
}
