package se.sinetiq.core.demo.application.provider.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfiguration {

    @Bean(name = "se.sinetiq.core.demo.application.provider.configuration.SpringDocConfiguration.apiInfo")
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Temperature Sensor API HTTP(S)-JSON")
                                .description("This is a simple API for accessing temperature sensor data.   Producer: The producer in this context is the temperature sensor along with its hosting system.  - GET /units: This endpoint returns a list of supported temperature units.  - GET /temperature: This endpoint provides the current temperature reading and associated metadata.  - GET /temperature/history: This endpoint provides historical temperature readings.  Consumer: The consumer is any application or system that needs to retrieve temperature data from the producer.   - GET /units: A consumer would make a GET request to this endpoint to find out what units of temperature the producer supports.  - GET /temperature: The consumer retrieves the current temperature reading and metadata by making a GET request to this endpoint.  - GET /temperature/history: The consumer retrieves historical temperature readings by making a GET request to this endpoint.  Procedure: Here is a typical interaction between a producer and a consumer:  - The consumer system sends a GET request to the /units endpoint. The producer system receives the request and responds with a list of supported temperature units. - The consumer system sends a GET request to the /temperature endpoint. The producer system receives the request and responds with the current temperature reading and related metadata. - The consumer system sends a GET request to the /temperature/history endpoint. The producer system receives the request and responds with the historical temperature readings. ")
                                .version("1.0.0")
                )
        ;
    }
}