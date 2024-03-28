package com.example.server.mappers;

import com.example.server.dto.MeasurementDto;
import com.example.server.models.Measurement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeasurementMapper {
    MeasurementDto toMeasurementDto(Measurement measurement);

    List<MeasurementDto> toMeasurementDtos(List<Measurement> measurements);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "sensor.id", ignore = true)
    Measurement toMeasurement(MeasurementDto measurementDto);
}
