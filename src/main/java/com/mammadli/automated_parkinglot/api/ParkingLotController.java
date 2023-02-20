package com.mammadli.automated_parkinglot.api;

import com.mammadli.automated_parkinglot.db.dto.ParkingLotDto;
import com.mammadli.automated_parkinglot.db.entity.ParkingLot;
import com.mammadli.automated_parkinglot.services.ParkingLotServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/parkingLots")
public class ParkingLotController {

    private final ParkingLotServices parkingLotServices;

    @PostMapping
    ResponseEntity<ParkingLot> create(@RequestBody ParkingLotDto parkingLotDto){
        return ResponseEntity.ok(parkingLotServices.create(parkingLotDto));
    }

    @DeleteMapping("/id/{parkingLotId}")
    ResponseEntity<Void> delete(@PathVariable String parkingLotId){
        return ResponseEntity.ok(parkingLotServices.delete(parkingLotId));
    }

}
