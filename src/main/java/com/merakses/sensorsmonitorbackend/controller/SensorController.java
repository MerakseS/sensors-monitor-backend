package com.merakses.sensorsmonitorbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merakses.sensorsmonitorbackend.dto.SensorRequestDto;
import com.merakses.sensorsmonitorbackend.dto.SensorResponseDto;
import com.merakses.sensorsmonitorbackend.entity.Sensor;
import com.merakses.sensorsmonitorbackend.mapper.SensorMapper;
import com.merakses.sensorsmonitorbackend.service.SensorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorMapper sensorMapper;
    private final SensorService sensorService;

    @PostMapping
    public ResponseEntity<SensorResponseDto> create(@RequestBody @Valid SensorRequestDto sensorRequestDTO) {
        Sensor sensor = sensorMapper.mapRequestDtoToSensor(sensorRequestDTO);
        sensor = sensorService.create(sensor);
        SensorResponseDto responseDto = sensorMapper.mapSensorToResponseDto(sensor);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}

