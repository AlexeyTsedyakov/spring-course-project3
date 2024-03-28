package com.example.server.mappers;

import com.example.server.dto.SensorDto;
import com.example.server.models.Sensor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SensorMapper {
    SensorDto toSensorDto(Sensor sensor);

    @Mapping(target = "id", ignore = true)
    Sensor toSensor(SensorDto sensorDro);
}
