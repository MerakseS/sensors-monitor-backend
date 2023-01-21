package com.merakses.sensorsmonitorbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.merakses.sensorsmonitorbackend.entity.Sensor;
import com.merakses.sensorsmonitorbackend.repository.SensorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class DefaultSensorService implements SensorService {

    private final SensorRepository sensorRepository;

    @Override
    @Transactional
    public Sensor create(Sensor sensor) {
        sensor = sensorRepository.save(sensor);
        log.info("Successfully created sensor with id {}", sensor.getId());
        return sensor;
    }

    @Override
    public List<Sensor> getAll() {
        List<Sensor> sensorList = sensorRepository.findAll();
        log.info("Successfully found all sensors");
        return sensorList;
    }
}
