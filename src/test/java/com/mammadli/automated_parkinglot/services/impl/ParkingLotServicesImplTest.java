package com.mammadli.automated_parkinglot.services.impl;

import com.mammadli.automated_parkinglot.db.dto.ParkingLotDto;
import com.mammadli.automated_parkinglot.db.entity.ParkingLot;
import com.mammadli.automated_parkinglot.db.repository.ParkingLotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ParkingLotServicesImpl.class})
@ExtendWith(SpringExtension.class)
class ParkingLotServicesImplTest {


    @MockBean
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingLotServicesImpl parkingLotServicesImpl;

    String id = "123";
    BigDecimal value = BigDecimal.valueOf(5.7);
    ParkingLotDto parkingLotDto = ParkingLotDto.builder()
            .price(value)
            .build();

    ParkingLot parkingLot = ParkingLot.builder()
            .id(id)
            .price(value)
            .build();


    @Test
    void testCreateParkingLotSuccess() {

        when(parkingLotRepository.save(any())).thenReturn(parkingLot);

        ParkingLot result = parkingLotServicesImpl.create(parkingLotDto);

        assertNotNull(result);
        assertEquals(parkingLot, result);
        verify(parkingLotRepository).save(any());
    }


    @Test
    void deleteParkingLotSuccess() {

        when(parkingLotRepository.findById(any())).thenReturn(Optional.of(parkingLot));

        Void result = parkingLotServicesImpl.delete(id);

        assertNull(result);
        verify(parkingLotRepository).findById(id);
        verify(parkingLotRepository).deleteById(id);

    }

    @Test
    void deleteParkingLotNotFound(){

        when(parkingLotRepository.findById(any())).thenReturn(Optional.empty());

        Void result = parkingLotServicesImpl.delete(id);

        assertNull(result);
        verify(parkingLotRepository).findById(id);
        verify(parkingLotRepository, never()).deleteById(any());
    }
}