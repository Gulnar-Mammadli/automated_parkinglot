package com.mammadli.automated_parkinglot.db.repository;

import com.mammadli.automated_parkinglot.db.entity.ParkingLot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends CrudRepository<ParkingLot,String> {

}
