package com.merakses.sensorsmonitorbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.merakses.sensorsmonitorbackend.entity.Sensor;
import com.merakses.sensorsmonitorbackend.exception.EntityNotFoundException;
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

    @Override
    public Sensor get(long id) {
        Sensor sensor = getSensorById(id);
        log.info("Successfully found sensor with id {}", sensor.getId());
        return sensor;
    }

    @Override
    @Transactional
    public Sensor update(long id, Sensor sensor) {
        getSensorById(id);

        sensor.setId(id);
        sensorRepository.save(sensor);
        log.info("Successfully updated sensor with id {}", sensor.getId());

        return sensor;
    }

    @Override
    @Transactional
    public void delete(long id) {
        Sensor sensor = getSensorById(id);
        sensorRepository.delete(sensor);
        log.info("Successfully deleted sensor with id {}", id);
    }

    private Sensor getSensorById(long id) {
        return sensorRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException(String.format("Can't find sensor with id %d", id))
        );
    }
}
