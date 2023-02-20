package com.mammadli.automated_parkinglot.db.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private int height;

    private int weight;

    private String floorId;

    private LocalDateTime parkingTime;
//TODO should have it?
    private LocalDateTime unparkingTime;

}
