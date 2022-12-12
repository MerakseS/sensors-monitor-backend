package com.merakses.sensorsmonitorbackend.entity;

import java.util.Objects;

import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "Name is required")
    @Length(message = "Name length can't be more than 30 symbols.", max = 30)
    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "model", length = 15)
    @NotBlank(message = "Model is required")
    @Length(message = "Model length can't be more than 15 symbols.", max = 15)
    private String model;

    @Column(name = "range_from")
    private int rangeFrom;

    @Column(name = "range_to")
    private int rangeTo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @Length(message = "Location length can't be more than 40 symbols.", max = 40)
    @Column(name = "location", length = 40)
    private String location;

    @Length(message = "Description length can't be more than 200 symbols.", max = 200)
    @Column(name = "description", length = 200)
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Sensor sensor = (Sensor) o;
        return id != null && Objects.equals(id, sensor.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}