package com.mammadli.automated_parkinglot.services;

import com.mammadli.automated_parkinglot.db.dto.CarDto;
import com.mammadli.automated_parkinglot.db.entity.Car;

import java.util.List;

public interface CarServices {
    Car parkNewCar(CarDto carDto);

    Void unParkCar(String carId,String parkingLotId);

    List<Car> getCars(String floorId);
}
