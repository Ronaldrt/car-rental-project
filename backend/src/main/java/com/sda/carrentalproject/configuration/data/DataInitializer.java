package com.sda.carrentalproject.configuration.data;

import com.sda.carrentalproject.domain.Car;
import com.sda.carrentalproject.domain.Client;
import com.sda.carrentalproject.domain.PriceList;
import com.sda.carrentalproject.domain.enumeration.Color;
import com.sda.carrentalproject.repository.CarRepository;
import com.sda.carrentalproject.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

@Component
@Slf4j
@Profile("develop")
public class DataInitializer implements CommandLineRunner {

    private final ClientRepository clientRepository;

    private final CarRepository carRepository;

    public DataInitializer(ClientRepository clientRepository, CarRepository carRepository) {
        this.clientRepository = clientRepository;
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createClients();
        createCars();
    }

    private void createCars() {
        log.info("Creating some cars");

        Car carAvailable = Car.builder()
                .brand("Audi")
                .model("A7")
                .productionYear(YearMonth.of(2015,Month.JANUARY))
                .color(Color.BLUE)
                .available(true)
                .priceList(new PriceList(10_000))
                .build();

        Car car2Available = Car.builder()
                .brand("Ford")
                .model("Focus")
                .productionYear(YearMonth.of(2000,Month.OCTOBER))
                .color(Color.BLUE)
                .available(true)
                .priceList(new PriceList(5_000))
                .build();

        Car car3Unavailable = Car.builder()
                .brand("Mazda")
                .model("MX-5")
                .productionYear(YearMonth.of(2020,Month.JANUARY))
                .color(Color.PINK)
                .available(false)
                .priceList(new PriceList(12_000))
                .build();

        carRepository.save(carAvailable);
        carRepository.save(car2Available);
        carRepository.save(car3Unavailable);


    }


    private void createClients(){
        log.info("creating some clients");
        Client client = Client.builder()
                .name("mariusz")
                .surname("p.")
                .phone("555-555-555")
                .email("mp@gmail.com")
                .address("poland")
                .dateOfBirth(LocalDate.of(1990, Month.JANUARY, 7))
                .build();

        clientRepository.save(client);
    }
}
