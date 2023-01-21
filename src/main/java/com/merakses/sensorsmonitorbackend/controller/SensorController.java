package com.merakses.sensorsmonitorbackend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping
    public ResponseEntity<List<SensorResponseDto>> getAll() {
        List<Sensor> sensorList = sensorService.getAll();
        return ResponseEntity.ok(sensorMapper.mapSensorListToResponseDtoList(sensorList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorResponseDto> get(@PathVariable long id) {
        Sensor sensor = sensorService.get(id);
        return ResponseEntity.ok(sensorMapper.mapSensorToResponseDto(sensor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SensorResponseDto> update(@PathVariable long id,
        @RequestBody @Valid SensorRequestDto sensorRequestDto) {

        Sensor sensor = sensorMapper.mapRequestDtoToSensor(sensorRequestDto);
        sensor = sensorService.update(id, sensor);
        return ResponseEntity.ok(sensorMapper.mapSensorToResponseDto(sensor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        sensorService.delete(id);
        return ResponseEntity.ok().build();
    }
}

