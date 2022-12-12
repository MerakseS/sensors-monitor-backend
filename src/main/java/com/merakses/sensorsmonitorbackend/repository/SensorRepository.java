package com.merakses.sensorsmonitorbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.merakses.sensorsmonitorbackend.entity.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

}
