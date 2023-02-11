package com.merakses.sensorsmonitorbackend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.merakses.sensorsmonitorbackend.entity.Sensor;

public interface SensorService {

    Sensor create(Sensor sensor);

    List<Sensor> getAll();

    Page<Sensor> getPage(int pageNumber);

    Sensor get(long id);

    Sensor update(long id, Sensor sensor);

    void delete(long id);
}
