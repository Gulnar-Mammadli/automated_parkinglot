package com.mammadli.automated_parkinglot.db.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FloorDto {

    private int ceilingHeight;

    private int weightCapacity;

    private int maxCapacity;

    private String parkingLotId;

}
