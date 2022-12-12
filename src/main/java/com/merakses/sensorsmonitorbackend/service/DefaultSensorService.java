package com.merakses.sensorsmonitorbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.merakses.sensorsmonitorbackend.entity.Sensor;

@Service
public class DefaultSensorService implements SensorService {

    @Override
    public List<Sensor> getAll() {
        return null;
    }
}
