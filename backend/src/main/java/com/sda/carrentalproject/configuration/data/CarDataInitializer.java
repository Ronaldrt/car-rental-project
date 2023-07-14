package com.sda.carrentalproject.configuration.data;

import com.sda.carrentalproject.domain.Car;
import com.sda.carrentalproject.domain.PriceList;
import com.sda.carrentalproject.domain.enumeration.Color;
import com.sda.carrentalproject.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
@Component
@Slf4j
@Profile("develop")
public class CarDataInitializer implements CommandLineRunner {

    private final CarRepository carRepository;

    private final PriceList priceList = new PriceList();

    public CarDataInitializer(CarRepository carRepository){
        this.carRepository = carRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        log.info("Creating some cars");
        Car car = Car.builder()
                .brand("Audi")
                .model("A7")
                .productionYear(YearMonth.of(2015,9))
                .color(Color.BLUE)
                .available(true)
                .priceList(PriceList.builder().pricePerDayInEuroCents(100).build())
                .build();

        carRepository.save(car);
    }
}
