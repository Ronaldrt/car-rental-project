package com.sda.carrentalproject.controller;

import com.sda.carrentalproject.domain.Car;

import com.sda.carrentalproject.dto.CarDto;

import com.sda.carrentalproject.mapper.CarMapper;
import com.sda.carrentalproject.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
@CrossOrigin("${frontend.trusted-url}")
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
        Car createdCar = carService.save(carMapper.fromDtoToEntity(carToSave));

        URI path = ucb.path("/api/cars/{id}")
                .buildAndExpand(Map.of("id", createdCar.getId()))
                .toUri();

        return ResponseEntity.created(path)
                .body(carMapper.fromEntityToDto(createdCar));
    }


    // /cars?available=true
    @GetMapping("/cars")
    public Page<CarDto> getCars(@RequestParam Map<String, String> queryParams,
                                Pageable pageable) {
        log.info("getting cars");
        log.info("query params: {}", queryParams);
        log.info("paging parameters: [{}]", pageable);

        return carService.findCarsBasedOnQueryParameters(queryParams, pageable)
                .map(car -> carMapper.fromEntityToDto(car));

    }
}
