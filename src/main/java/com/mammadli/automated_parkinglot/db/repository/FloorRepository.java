package com.mammadli.automated_parkinglot.db.repository;

import com.mammadli.automated_parkinglot.db.entity.Floor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends CrudRepository<Floor, String> {

    List<Floor> findAllByParkingLotId(String parkingLotId);

    List<Floor> findAllByCeilingHeightGreaterThan(int carHeight);

}
