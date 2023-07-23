package com.sda.carrentalproject.controller;

import com.sda.carrentalproject.domain.Car;
import com.sda.carrentalproject.domain.Client;
import com.sda.carrentalproject.dto.CarDto;
import com.sda.carrentalproject.dto.ClientDto;
import com.sda.carrentalproject.mapper.CarMapper;
import com.sda.carrentalproject.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
@CrossOrigin("*")
public class CarController {

    private final CarService carService;

    private final CarMapper carMapper;

    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

//    @GetMapping("/cars")
//    List<CarDto> allCars() {
//        log.info("all cars endpoint");
//        var cars = carService.getAllCars();
//
//        return cars.stream()
//                .map(carMapper::fromEntityToDto)
//                .toList();
//    }


    @PostMapping("/cars")
    ResponseEntity<CarDto> createNewCar(@RequestBody CarDto carToSave,
                                        UriComponentsBuilder ucb) {
        log.info("trying to save new car: [{}]", carToSave);
        Car createdCar = carService.saveCar(carMapper.fromDtoToEntity(carToSave));

        URI path = ucb.path("/api/cars/{id}")
                .buildAndExpand(Map.of("id", createdCar.getId()))
                .toUri();

        return ResponseEntity.created(path)
                .body(carMapper.fromEntityToDto(createdCar));
    }


    // /cars?available=true
    @GetMapping("/cars")
    public List<CarDto> getCars(@RequestParam Map<String, String> queryParams) {
        log.info("getting cars");
        log.info("query params: {}", queryParams);
        return carService.findCarsBasedOnQueryParameters(queryParams)
                .stream()
                .map(car -> carMapper.fromEntityToDto(car))
                .toList();
    }
}
