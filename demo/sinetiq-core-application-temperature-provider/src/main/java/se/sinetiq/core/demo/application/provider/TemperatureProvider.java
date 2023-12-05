package se.sinetiq.core.demo.application.provider;

import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
@ComponentScan(
    basePackages = {"se.sinetiq.core.demo.application.provider", "se.sinetiq.core.demo.application.provider.api" , "se.sinetiq.core.demo.application.provider.configuration"},
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
public class TemperatureProvider {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TemperatureProvider.class, args);
    }

    @Bean(name = "se.sinetiq.core.demo.application.provider.TemperatureProvider.jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}