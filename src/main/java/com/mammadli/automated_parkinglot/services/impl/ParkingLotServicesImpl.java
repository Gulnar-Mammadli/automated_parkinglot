package com.mammadli.automated_parkinglot.services.impl;

import com.mammadli.automated_parkinglot.db.dto.ParkingLotDto;
import com.mammadli.automated_parkinglot.db.entity.ParkingLot;
import com.mammadli.automated_parkinglot.mapper.ParkingLotMapper;
import com.mammadli.automated_parkinglot.db.repository.ParkingLotRepository;
import com.mammadli.automated_parkinglot.services.ParkingLotServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ParkingLotServicesImpl implements ParkingLotServices {

    private final ParkingLotRepository parkingLotRepository;

    @Override
    public ParkingLot create(ParkingLotDto parkingLotDto) {

        return parkingLotRepository.save(ParkingLotMapper.INSTANCE.fromParkingLotDto(parkingLotDto));
    }

    @Override
    public Void delete(String parkingLotId) {

        Optional<ParkingLot> parkingLot = parkingLotRepository.findById(parkingLotId);
        if(parkingLot.isPresent()){
            parkingLotRepository.deleteById(parkingLotId);
        }
        return null;
    }

}
