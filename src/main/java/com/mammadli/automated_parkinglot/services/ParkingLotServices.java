package com.mammadli.automated_parkinglot.services;

import com.mammadli.automated_parkinglot.db.dto.ParkingLotDto;
import com.mammadli.automated_parkinglot.db.entity.ParkingLot;

public interface ParkingLotServices {
    ParkingLot create(ParkingLotDto parkingLotDto);
    Void delete(String parkingLotId);

}
