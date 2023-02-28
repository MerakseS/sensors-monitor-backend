package com.merakses.sensorsmonitorbackend.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.merakses.sensorsmonitorbackend.dto.SensorRequestDto;
import com.merakses.sensorsmonitorbackend.dto.SensorResponseDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public interface SensorController {

    @PostMapping
    ResponseEntity<SensorResponseDto> create(@RequestBody @Valid SensorRequestDto sensorRequestDTO);

    @GetMapping
    ResponseEntity<List<SensorResponseDto>> getAll();

    @GetMapping("/page/{page}")
    ResponseEntity<Page<SensorResponseDto>> getPage(@PathVariable("page") @Min(0) int pageNumber);

    @GetMapping("/{id}")
    ResponseEntity<SensorResponseDto> get(@PathVariable long id);

    @PutMapping("/{id}")
    ResponseEntity<SensorResponseDto> update(@PathVariable long id, @RequestBody @Valid SensorRequestDto sensorRequestDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Object> delete(@PathVariable long id);
}
