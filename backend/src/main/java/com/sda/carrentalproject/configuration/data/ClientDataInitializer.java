package com.sda.carrentalproject.configuration.data;

import com.sda.carrentalproject.domain.Client;
import com.sda.carrentalproject.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
@Slf4j
@Profile("develop")
public class ClientDataInitializer implements CommandLineRunner {

    private final ClientRepository clientRepository;

    public ClientDataInitializer(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Creating some clients");

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
