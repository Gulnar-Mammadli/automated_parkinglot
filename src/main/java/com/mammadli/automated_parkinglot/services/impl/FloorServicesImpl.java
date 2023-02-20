package com.mammadli.automated_parkinglot.services.impl;

import com.mammadli.automated_parkinglot.db.dto.FloorDto;
import com.mammadli.automated_parkinglot.db.entity.Car;
import com.mammadli.automated_parkinglot.db.entity.Floor;
import com.mammadli.automated_parkinglot.mapper.FloorMapper;
import com.mammadli.automated_parkinglot.db.repository.CarRepository;
import com.mammadli.automated_parkinglot.db.repository.FloorRepository;
import com.mammadli.automated_parkinglot.services.FloorServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FloorServicesImpl implements FloorServices {

    private final FloorRepository floorRepository;

    private final CarRepository carRepository;

    @Override
    public Floor create(FloorDto floorDto) {

        Floor floor = FloorMapper.INSTANCE.fromFloorDto(floorDto);
        return floorRepository.save(floor);
    }

    @Override
    public Void delete(String floorId) {

        Optional<Floor> floor = floorRepository.findById(floorId);
        if (floor.isPresent()) {
            List<Car> cars = carRepository.findAllByFloorId(floorId);
            carRepository.deleteAll(cars);
            floorRepository.deleteById(floorId);
        }
        return null;
    }

    @Override
    public List<Floor> getFloors(String parkingLotId) {

        List<Floor> floors = floorRepository.findAllByParkingLotId(parkingLotId);
        if (!floors.isEmpty()) {
            return floors;
        }
        return null;
    }

}
