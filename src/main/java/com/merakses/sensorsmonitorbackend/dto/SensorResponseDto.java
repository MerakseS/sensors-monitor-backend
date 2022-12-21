package com.merakses.sensorsmonitorbackend.dto;

import lombok.Data;

@Data
public class SensorResponseDto {

    private Long id;

    private String name;

    private String model;

    private int rangeFrom;

    private int rangeTo;

    private long typeId;

    private long unitId;

    private String location;

    private String description;
}
