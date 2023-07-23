package com.sda.carrentalproject.service;

import com.sda.carrentalproject.domain.Car;
import com.sda.carrentalproject.domain.Client;
import com.sda.carrentalproject.exception.WrongCarIdException;
import com.sda.carrentalproject.exception.WrongClientIdException;
import com.sda.carrentalproject.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class CarService {

    private static final String availableKey = "available";
    private static final String colorKey = "color";
    private static final String brandKey = "brand";
    private static final String modelKey = "model";

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        log.info("getting all cars from repository");

        var result = carRepository.findAll();

        log.info("found [{}] cars", result.size());
        log.debug("results: {}", result);

        return result;
    }

    public Car saveCar(Car carToBook) {
        log.info("creating new car: [{}]", carToBook);

        return carRepository.save(carToBook);
    }

    public Car findCarWithId(long id) {
        log.info("Trying to find a car with id: [{}]", id);

        return carRepository.findById(id)
                .map(car -> {
                    log.info("Found car: [{}]", car);
                    return car;
                })
                .orElseThrow(() -> new WrongCarIdException("No car with given id: [%s]".formatted(id)));
    }

    public List<Car> findAllCarsAvailableForBooking() {
        log.info("trying to find all cars available for booking");
        var availableCars = carRepository.findAllByAvailableTrue();
        log.info("number of available cars: [{}]", availableCars.size());
        log.debug("available cars: {}", availableCars);
        return availableCars;
    }



    public Car findAvailableCarWithId(long id) {
        log.info("Trying to find available car with given id: [{}]", id);
        return carRepository.findByIdAndAvailableTrue(id)
                .map(car -> {
                    log.info("Found available car: [{}]", car);
                    return car;
                })
                .orElseThrow(() -> new WrongCarIdException("Car with given id: [%s] is unavailable!".formatted(id)));
    }

    public List<Car> findCarsBasedOnQueryParameters(Map<String, String> queryParams) {
        log.info("finding cars based on query parameters: {}", queryParams);

        String availableValue = queryParams.getOrDefault(availableKey, "false");
        boolean available = Boolean.parseBoolean(availableValue);
        if(available){
            return findAllCarsAvailableForBooking();
        } else {
            return getAllCars();
        }
    }
}
