package com.merakses.sensorsmonitorbackend.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.merakses.sensorsmonitorbackend.dto.SensorRequestDto;
import com.merakses.sensorsmonitorbackend.dto.SensorResponseDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RequestMapping("/api/sensors")
public interface SensorController {

    @PostMapping
    ResponseEntity<SensorResponseDto> create(@RequestBody @Valid SensorRequestDto sensorRequestDTO);

    @GetMapping
    ResponseEntity<Page<SensorResponseDto>> getPage(@RequestParam(name = "page", defaultValue = "0") @Min(0) int pageNumber);

    @GetMapping("/search")
    ResponseEntity<Page<SensorResponseDto>> search(@RequestParam(name = "text") String searchText,
        @RequestParam(name = "page", defaultValue = "0") @Min(0) int pageNumber);

    @GetMapping("/{id}")
    ResponseEntity<SensorResponseDto> get(@PathVariable long id);

    @PutMapping("/{id}")
    ResponseEntity<SensorResponseDto> update(@PathVariable long id,
        @RequestBody @Valid SensorRequestDto sensorRequestDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Object> delete(@PathVariable long id);
}
