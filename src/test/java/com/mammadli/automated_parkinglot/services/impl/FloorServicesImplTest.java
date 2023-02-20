package com.mammadli.automated_parkinglot.services.impl;

import com.mammadli.automated_parkinglot.db.dto.FloorDto;
import com.mammadli.automated_parkinglot.db.entity.Floor;
import com.mammadli.automated_parkinglot.db.repository.CarRepository;
import com.mammadli.automated_parkinglot.db.repository.FloorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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


    String parkingLotId = "12345";

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
    void delete() {


    }

    @Test
    void getFloors() {
    }
}