package com.merakses.sensorsmonitorbackend.controller.impl;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.merakses.sensorsmonitorbackend.controller.SensorController;
import com.merakses.sensorsmonitorbackend.dto.SensorRequestDto;
import com.merakses.sensorsmonitorbackend.dto.SensorResponseDto;
import com.merakses.sensorsmonitorbackend.entity.Sensor;
import com.merakses.sensorsmonitorbackend.mapper.SensorMapper;
import com.merakses.sensorsmonitorbackend.service.SensorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class DefaultSensorController implements SensorController {

    private final SensorMapper sensorMapper;
    private final SensorService sensorService;

    @Override
    public ResponseEntity<SensorResponseDto> create(SensorRequestDto sensorRequestDTO) {
        Sensor sensor = sensorMapper.mapRequestDtoToSensor(sensorRequestDTO);
        sensor = sensorService.create(sensor);
        SensorResponseDto responseDto = sensorMapper.mapSensorToResponseDto(sensor);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Override
    public ResponseEntity<Page<SensorResponseDto>> getPage(int pageNumber) {
        Page<Sensor> sensorPage = sensorService.getPage(pageNumber);
        return ResponseEntity.ok(sensorPage.map(sensorMapper::mapSensorToResponseDto));
    }

    @Override
    public ResponseEntity<Page<SensorResponseDto>> search(String searchText, int pageNumber) {
        Page<Sensor> sensorPage = sensorService.search(searchText, pageNumber);
        return ResponseEntity.ok(sensorPage.map(sensorMapper::mapSensorToResponseDto));
    }

    @Override
    public ResponseEntity<SensorResponseDto> get(long id) {
        Sensor sensor = sensorService.get(id);
        return ResponseEntity.ok(sensorMapper.mapSensorToResponseDto(sensor));
    }

    @Override
    public ResponseEntity<SensorResponseDto> update(long id, SensorRequestDto sensorRequestDto) {
        Sensor sensor = sensorMapper.mapRequestDtoToSensor(sensorRequestDto);
        sensor = sensorService.update(id, sensor);
        return ResponseEntity.ok(sensorMapper.mapSensorToResponseDto(sensor));
    }

    @Override
    public ResponseEntity<Object> delete(long id) {
        sensorService.delete(id);
        return ResponseEntity.ok().build();
    }
}

