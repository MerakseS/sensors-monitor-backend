package com.merakses.sensorsmonitorbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.merakses.sensorsmonitorbackend.entity.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    @Query(value = """
        SELECT s
        FROM Sensor s
        WHERE
        s.name LIKE %:text%
        OR s.model LIKE %:text%
        OR CAST(s.rangeFrom AS string) LIKE %:text%
        OR CAST(s.rangeTo AS string) LIKE %:text%
        OR s.location LIKE %:text%
        OR s.description LIKE %:text%
        OR s.type.name LIKE %:text%
        OR s.unit.name LIKE %:text%
        """)
    Page<Sensor> findAllByAllFields(String text, Pageable pageable);
}
