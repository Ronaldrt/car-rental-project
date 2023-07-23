package com.sda.carrentalproject.service;
import com.sda.carrentalproject.domain.Car;
import com.sda.carrentalproject.exception.WrongCarIdException;
import com.sda.carrentalproject.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Collections;
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
    public Car findCarWithId(long id) {
        log.info("trying to find car with id: [{}]", id);
        return carRepository.findById(id)
                .map(car -> {
                    log.info("Found car: [{}]", car);
                    return car;
                })
                .orElseThrow(() -> new WrongCarIdException("No car with given id: [%s]".formatted(id)));
    }
    public Car save(Car carToBook) {
        log.info("trying to save new car: [{}]", carToBook);
        return carRepository.save(carToBook);
    }
    public Car findAvailableCarWithId(long id) {
        log.info("trying to find available car with given id: [{}]", id);
        return carRepository.findByIdAndAvailableTrue(id)
                .map(car -> {
                    log.info("Found available car: [{}]", car);
                    return car;
                })
                .orElseThrow(() -> new WrongCarIdException("Car with given id: [%s] is unavailable!".formatted(id)));
    }
    public List<Car> findAllCars() {
        log.info("trying to find all cars");
        var allCars = carRepository.findAll();
        log.info("number of all cars: [{}]", allCars.size());
        log.debug("all cars: {}", allCars);
        return allCars;
    }
    public List<Car> findAllCarsAvailableForBooking() {
        log.info("trying to find all cars available for booking");
        var availableCars = carRepository.findAllByAvailable(true);
        log.info("number of available cars: [{}]", availableCars.size());
        log.debug("available cars: {}", availableCars);
        return availableCars;
    }

    public List<Car> findRentCars(){
        log.info("trying to find rent cars");
        var rentCars = carRepository.findAllByAvailable(false);
        log.info("number of rent cars: [{}]", rentCars.size());
        log.debug("rent cars: [{}]", rentCars);
        return rentCars;

    }


    public List<Car> findCarsBasedOnQueryParameters(Map<String, String> queryParams) {
        log.info("finding cars based on query parameters: {}", queryParams);

        List<Car> result;
        if(!queryParams.containsKey(availableKey)){
            result = findAllCars();
        }else{
            boolean available = Boolean.parseBoolean(queryParams.get(availableKey));
            if(available){
                result = findAllCarsAvailableForBooking();
            }else{
                result = findRentCars();
            }
        }
        return result;
    }
}