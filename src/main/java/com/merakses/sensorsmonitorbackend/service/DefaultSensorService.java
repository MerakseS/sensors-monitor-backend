package com.merakses.sensorsmonitorbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private static final int PAGE_SIZE = 4;

    private final SensorRepository sensorRepository;

    @Override
    @Transactional
    public Sensor create(Sensor sensor) {
        sensor = sensorRepository.save(sensor);
        log.info("Successfully created sensor with id {}", sensor.getId());
        return sensor;
    }

    @Override
    public Page<Sensor> getPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        Page<Sensor> sensorPage = sensorRepository.findAll(pageable);
        log.info("Successfully found page {} of sensors", pageNumber);
        return sensorPage;
    }

    @Override
    public Page<Sensor> search(String searchText, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        Page<Sensor> sensorPage = sensorRepository.findAllByAllFields(searchText, pageable);
        log.info("Successfully found page {} of sensors by text {}", pageable, searchText);
        return sensorPage;
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
