package com.merakses.sensorsmonitorbackend.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

//TODO Range validation
@Data
public class SensorRequestDto {

    @NotBlank(message = "Name is required")
    @Length(message = "Name length can't be more than 30 symbols.", max = 30)
    private String name;

    @NotBlank(message = "Model is required")
    @Length(message = "Model length can't be more than 15 symbols.", max = 15)
    private String model;

    private int rangeFrom;

    private int rangeTo;

    private long typeId;

    private long unitId;

    @Length(message = "Location length can't be more than 40 symbols.", max = 40)
    private String location;

    @Length(message = "Description length can't be more than 200 symbols.", max = 200)
    private String description;

}
