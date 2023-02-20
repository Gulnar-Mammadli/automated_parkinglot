package com.mammadli.automated_parkinglot.mapper;

import com.mammadli.automated_parkinglot.db.dto.ParkingLotDto;
import com.mammadli.automated_parkinglot.db.entity.ParkingLot;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public abstract class ParkingLotMapper {

    public static final ParkingLotMapper INSTANCE = Mappers.getMapper(ParkingLotMapper.class);

    public abstract ParkingLot fromParkingLotDto(ParkingLotDto parkingLotDto);

    public abstract ParkingLotDto toParkingLotDto(ParkingLot parkingLot);
}
