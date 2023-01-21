package com.merakses.sensorsmonitorbackend.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.merakses.sensorsmonitorbackend.dto.SensorRequestDto;
import com.merakses.sensorsmonitorbackend.dto.SensorResponseDto;
import com.merakses.sensorsmonitorbackend.entity.Sensor;
import com.merakses.sensorsmonitorbackend.entity.Type;
import com.merakses.sensorsmonitorbackend.entity.Unit;
import com.merakses.sensorsmonitorbackend.exception.EntityNotFoundException;
import com.merakses.sensorsmonitorbackend.repository.TypeRepository;
import com.merakses.sensorsmonitorbackend.repository.UnitRepository;

@Mapper(componentModel = "spring")
public abstract class SensorMapper {

    @Autowired
    protected TypeRepository typeRepository;

    @Autowired
    protected UnitRepository unitRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", source = "typeId")
    @Mapping(target = "unit", source = "unitId")
    public abstract Sensor mapRequestDtoToSensor(SensorRequestDto requestDto);

    @Mapping(target = "type", source = "type.name")
    @Mapping(target = "unit", source = "unit.name")
    public abstract SensorResponseDto mapSensorToResponseDto(Sensor sensor);

    public abstract List<SensorResponseDto> mapSensorListToResponseDtoList(List<Sensor> sensorList);

    protected Type getTypeById(long typeId) {
        return typeRepository.findById(typeId).orElseThrow(() ->
            new EntityNotFoundException(String.format("Can't find type with id %d", typeId)));
    }

    protected Unit getUnitById(long unitId) {
        return unitRepository.findById(unitId).orElseThrow(() ->
            new EntityNotFoundException(String.format("Can't find type with id %d", unitId)));
    }
}
