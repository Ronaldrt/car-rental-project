package com.sda.carrentalproject.service;

import com.sda.carrentalproject.domain.Car;
import com.sda.carrentalproject.domain.Client;
import com.sda.carrentalproject.exception.WrongCarIdException;
import com.sda.carrentalproject.exception.WrongClientIdException;
import com.sda.carrentalproject.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars(){
        log.info("getting all cars from repository");

        var result = carRepository.findAll();

        log.info("found [{}] cars", result.size());
        log.debug("results: {}", result);

        return result;
    }

    public Car saveCar(Car carEntity) {
        log.info("creating new car: [{}]", carEntity);

        var result = carRepository.save(carEntity);
        log.info("saved client: [{}]", result);

        return result;
    }

    public Car findCarWithId(long id){
        log.info("Trying to find a car with id: [{}]", id);

       return carRepository.findById(id)
                .map(car -> {
                    log.info("Found car: [{}]", car);
                    return car;
                })
                .orElseThrow(() -> new WrongCarIdException("No car with given id: [%s]".formatted(id)));
    }

}
