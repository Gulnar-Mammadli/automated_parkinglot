package com.mammadli.automated_parkinglot.mapper;

import com.mammadli.automated_parkinglot.db.dto.FloorDto;
import com.mammadli.automated_parkinglot.db.entity.Floor;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public abstract class FloorMapper {

    public static final FloorMapper INSTANCE = Mappers.getMapper(FloorMapper.class);

    public abstract Floor fromFloorDto(FloorDto floorDto);

    public abstract FloorDto toFloorDto(Floor floor);
}
