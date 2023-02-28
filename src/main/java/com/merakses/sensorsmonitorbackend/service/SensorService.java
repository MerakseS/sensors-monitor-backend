package com.merakses.sensorsmonitorbackend.service;

import org.springframework.data.domain.Page;

import com.merakses.sensorsmonitorbackend.entity.Sensor;

public interface SensorService {

    Sensor create(Sensor sensor);

    Page<Sensor> getPage(int pageNumber);

    Page<Sensor> search(String searchText, int pageNumber);

    Sensor get(long id);

    Sensor update(long id, Sensor sensor);

    void delete(long id);
}
