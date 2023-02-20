package com.mammadli.automated_parkinglot.db.repository;

import com.mammadli.automated_parkinglot.db.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car,String> {
    List<Car> findAllByFloorId(String floorId);
}
