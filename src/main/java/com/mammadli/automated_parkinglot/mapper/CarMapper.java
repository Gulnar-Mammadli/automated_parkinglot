package com.mammadli.automated_parkinglot.mapper;

import com.mammadli.automated_parkinglot.db.dto.CarDto;
import com.mammadli.automated_parkinglot.db.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public abstract class CarMapper {

    public final static CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    public abstract Car mapToCar(CarDto carDto);

    public abstract CarDto mapToCarDto(Car car);
}
