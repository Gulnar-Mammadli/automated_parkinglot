package com.mammadli.automated_parkinglot.services.impl;

import com.mammadli.automated_parkinglot.db.dto.FloorDto;
import com.mammadli.automated_parkinglot.db.entity.Car;
import com.mammadli.automated_parkinglot.db.entity.Floor;
import com.mammadli.automated_parkinglot.db.entity.ParkingLot;
import com.mammadli.automated_parkinglot.db.repository.CarRepository;
import com.mammadli.automated_parkinglot.db.repository.FloorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {FloorServicesImpl.class})
@ExtendWith(SpringExtension.class)
class FloorServicesImplTest {

    @MockBean
    private FloorRepository floorRepository;

    @Autowired
    private FloorServicesImpl floorServicesImpl;

    @MockBean
    private CarRepository carRepository;


    private static final String parkingLotId = "12345";
    private  static final String floorId = "123";

    FloorDto floorDto = FloorDto.builder()
            .parkingLotId(parkingLotId)
            .ceilingHeight(10)
            .maxCapacity(30)
            .weightCapacity(4000)
            .build();

    Floor floor = Floor.builder()
            .parkingLotId(parkingLotId)
            .ceilingHeight(10)
            .maxCapacity(30)
            .weightCapacity(4000)
            .build();

    Floor floor1 = Floor.builder()
            .parkingLotId(parkingLotId)
            .ceilingHeight(20)
            .maxCapacity(14)
            .weightCapacity(2000)
            .build();
    Car car1 = Car.builder()
            .id("12")
            .floorId(floorId)
            .height(30)
            .weight(100)
            .parkingTime(LocalDateTime.now().minusMinutes(5))
            .unparkingTime(LocalDateTime.now())
            .build();

    Car car2 = Car.builder()
            .id("13")
            .floorId(floorId)
            .height(40)
            .weight(120)
            .parkingTime(LocalDateTime.now().minusMinutes(7))
            .unparkingTime(LocalDateTime.now())
            .build();

    List<Car> carsList = Arrays.asList(car1,car2);
    List<Floor> floorList = Arrays.asList(floor,floor1);

    ParkingLot parkingLot = ParkingLot.builder()
            .id("1234567")
            .price(BigDecimal.valueOf(5.9))
            .build();

    @Test
    void createFloorSuccess() {
        //Arrange
//        when(this.floorMapper.fromFloorDto(any())).thenReturn(floor);
        when(floorRepository.save(any())).thenReturn(floor);

        //Act
        Floor result = floorServicesImpl.create(floorDto);

        //Assert
        assertEquals(floor,result);
        assertNotNull(result);
//        verify(this.floorMapper).fromFloorDto(floorDto);
        verify(floorRepository).save(floor);
    }

    @Test
    void deleteFloorSuccess() {
        //Arrange
        when(floorRepository.findById(any())).thenReturn(Optional.of(floor));
        when(carRepository.findAllByFloorId(any())).thenReturn(carsList);

        //Act
        floorServicesImpl.delete(floorId);

        //Assert
        verify(floorRepository).findById(floorId);
        verify(carRepository).findAllByFloorId(floorId);

    }

    @Test
    void deleteFloorNotFound(){
        //Arrange
        when(floorRepository.findById(any())).thenReturn(Optional.empty());

        //Act
        floorServicesImpl.delete(floorId);

        //Assert
        verify(floorRepository).findById(floorId);
    }

    @Test
    void getFloorsSuccess() {
        //Arrange
        when(floorRepository.findAllByParkingLotId(any())).thenReturn(floorList);

        //Act
        List<Floor> result = floorServicesImpl.getFloors(parkingLotId);

        //Assert
        assertEquals(floorList,result);
        assertNotNull(result);
        verify(floorRepository).findAllByParkingLotId(parkingLotId);
    }

    @Test
    void getFloorsNotFound() {
        //Arrange
        when(floorRepository.findAllByParkingLotId(any())).thenReturn(Collections.emptyList());

        //Act
        List<Floor> result = floorServicesImpl.getFloors(parkingLot.getId());

        //Assert
        assertNull(result);
        verify(floorRepository).findAllByParkingLotId(parkingLot.getId());
    }
}