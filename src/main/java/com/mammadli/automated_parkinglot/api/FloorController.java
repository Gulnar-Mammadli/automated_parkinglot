package com.mammadli.automated_parkinglot.api;

import com.mammadli.automated_parkinglot.db.dto.FloorDto;
import com.mammadli.automated_parkinglot.db.entity.Floor;
import com.mammadli.automated_parkinglot.services.FloorServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/floors")
public class FloorController {

    private final FloorServices floorServices;

    @PostMapping
    ResponseEntity<Floor> create(@RequestBody FloorDto floorDto){
        return ResponseEntity.ok(floorServices.create(floorDto));
    }

    @GetMapping("/parkingLotId/{parkingLotId}")
    ResponseEntity<List<Floor>> getFloors(@PathVariable String parkingLotId){
        return ResponseEntity.ok(floorServices.getFloors(parkingLotId));
    }

    @DeleteMapping("/id/{floorId}")
    ResponseEntity<Void> delete(@PathVariable String floorId){
        return ResponseEntity.ok(floorServices.delete(floorId));
    }

}
