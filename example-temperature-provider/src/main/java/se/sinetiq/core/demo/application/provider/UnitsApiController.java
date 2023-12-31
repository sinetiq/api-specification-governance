package se.sinetiq.example.application.provider;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import se.sinetiq.example.application.provider.api.UnitsApi;
import se.sinetiq.example.application.provider.model.Units;

import java.util.Arrays;

@Controller
@RequestMapping
public class UnitsApiController implements UnitsApi {
    public ResponseEntity<Units> unitsGet() {
        return ResponseEntity.ok(new Units().units(Arrays.asList("C", "F", "K")));
    }
}
