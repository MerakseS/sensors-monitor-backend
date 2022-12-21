package com.merakses.sensorsmonitorbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.merakses.sensorsmonitorbackend.dto.SensorRequestDto;
import com.merakses.sensorsmonitorbackend.dto.SensorResponseDto;
import com.merakses.sensorsmonitorbackend.entity.Sensor;
import com.merakses.sensorsmonitorbackend.entity.Type;
import com.merakses.sensorsmonitorbackend.entity.Unit;
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

    @Mapping(target = "typeId", source = "type.id")
    @Mapping(target = "unitId", source = "unit.id")
    public abstract SensorResponseDto mapSensorToResponseDto(Sensor sensor);

    protected Type getTypeById(long typeId) {
        return typeRepository.findById(typeId).orElseThrow();
    }

    protected Unit getUnitById(long unitId) {
        return unitRepository.findById(unitId).orElseThrow();
    }
}
