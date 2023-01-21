package com.merakses.sensorsmonitorbackend.service;

import java.util.List;

import com.merakses.sensorsmonitorbackend.entity.Sensor;

public interface SensorService {

    Sensor create(Sensor sensor);

    List<Sensor> getAll();

    Sensor get(long id);

    Sensor update(long id, Sensor sensor);
}
