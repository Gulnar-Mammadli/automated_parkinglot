package com.mammadli.automated_parkinglot.services;

import com.mammadli.automated_parkinglot.db.dto.FloorDto;
import com.mammadli.automated_parkinglot.db.entity.Floor;

import java.util.List;

public interface FloorServices {
    Floor create(FloorDto floorDto);
    Void delete(String floorId);

    List<Floor> getFloors(String parkingLotId);
}
