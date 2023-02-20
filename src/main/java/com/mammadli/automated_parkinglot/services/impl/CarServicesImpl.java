package com.mammadli.automated_parkinglot.services.impl;

import com.mammadli.automated_parkinglot.db.dto.CarDto;
import com.mammadli.automated_parkinglot.db.entity.Car;
import com.mammadli.automated_parkinglot.db.entity.Floor;
import com.mammadli.automated_parkinglot.db.entity.ParkingLot;
import com.mammadli.automated_parkinglot.mapper.CarMapper;
import com.mammadli.automated_parkinglot.db.repository.CarRepository;
import com.mammadli.automated_parkinglot.db.repository.FloorRepository;
import com.mammadli.automated_parkinglot.db.repository.ParkingLotRepository;
import com.mammadli.automated_parkinglot.services.CarServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServicesImpl implements CarServices {

    private final CarRepository carRepository;

    private final FloorRepository floorRepository;

    private final ParkingLotRepository parkingLotRepository;

    @Override
    public Car parkNewCar(CarDto carDto) {

        Car car = CarMapper.INSTANCE.mapToCar(carDto);
        List<Floor> floors = floorRepository.findAllByCeilingHeightGreaterThan(car.getHeight());
        Optional<Floor> firstFloor = floors.stream()
                .min(Comparator.comparing(Floor::getCeilingHeight))
                .filter(floor -> checkWeightAvailability(car.getWeight(), floor))
                .filter(this::checkMaxCapacity);

        if (firstFloor.isPresent()) {
            firstFloor.get().setCurrentWeightCapacity(firstFloor.get().getCurrentWeightCapacity() + car.getWeight());
            car.setFloorId(firstFloor.get().getId());
            car.setParkingTime(LocalDateTime.now());
            carRepository.save(car);
            return car;
        }
        return null;
    }

    private boolean checkWeightAvailability(int carWeight, Floor floor) {
        List<Car> cars = carRepository.findAllByFloorId(floor.getId());
        if (!cars.isEmpty()) {
            int currentCapacity = floor.getCurrentWeightCapacity();
            int newWeight = currentCapacity + carWeight;
            if (newWeight > floor.getWeightCapacity()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkMaxCapacity(Floor floor) {
        List<Car> cars = carRepository.findAllByFloorId(floor.getId());
        if (!cars.isEmpty()) {
            int numberOfCars = cars.size() + 1;
            if (numberOfCars > floor.getMaxCapacity()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Void unParkCar(String carId, String parkingLotId) {
        Optional<Car> car = carRepository.findById(carId);
        Optional<ParkingLot> parkingLot = parkingLotRepository.findById(parkingLotId);
        if (parkingLot.isPresent()) {
            if (car.isPresent()) {
                car.get().setUnparkingTime(LocalDateTime.now());
                long totalTime = Duration.between(car.get().getParkingTime(), car.get().getUnparkingTime()).toMinutes();
                BigDecimal totalBill = BigDecimal.valueOf(totalTime).multiply(parkingLot.get().getPrice());
                Optional<Floor> floor = floorRepository.findById(car.get().getFloorId());
                floor.ifPresent(value -> value.setCurrentWeightCapacity(value.getCurrentWeightCapacity() - car.get().getWeight()));
                car.get().setFloorId(null);
                if (pay(totalBill, carId)) {
                    carRepository.save(car.get());
                }
            }
        }
        return null;
    }


    private boolean pay(BigDecimal bill, String carId) {
        return true;
    }

    @Override
    public List<Car> getCars(String floorId) {
        List<Car> cars = carRepository.findAllByFloorId(floorId);
        if (!cars.isEmpty()) {
            return cars;
        }
        return null;
    }

}
