package com.example.travelg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class TravelgApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelgApplication.class, args);
    }
}
