package com.mammadli.automated_parkinglot.api;

import com.mammadli.automated_parkinglot.db.dto.CarDto;
import com.mammadli.automated_parkinglot.db.entity.Car;
import com.mammadli.automated_parkinglot.services.CarServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarServices carServices;

    @PostMapping
    ResponseEntity<Car> parkNewCar(@RequestBody CarDto carDto){
        return ResponseEntity.ok(carServices.parkNewCar(carDto));
    }

    @GetMapping("/floorId/{floorId}")
    ResponseEntity<List<Car>> getCars(@PathVariable String floorId){
        return ResponseEntity.ok(carServices.getCars(floorId));
    }

    @DeleteMapping("/carId/parkingLotId/{carId}/{parkingLotId}")
    ResponseEntity<Void> unParkCar(@PathVariable String carId, @PathVariable String parkingLotId){
        return ResponseEntity.ok(carServices.unParkCar(carId,parkingLotId));
    }
}
