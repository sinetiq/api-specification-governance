package se.sinetiq.example.application.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TemperatureProvider {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TemperatureProvider.class, args);
    }
}