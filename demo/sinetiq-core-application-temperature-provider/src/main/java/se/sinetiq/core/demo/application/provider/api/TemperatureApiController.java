package se.sinetiq.core.demo.application.provider.api;

import org.springframework.format.annotation.DateTimeFormat;
import se.sinetiq.core.demo.application.provider.model.HistoricalTemperatureData;
import java.time.OffsetDateTime;
import se.sinetiq.core.demo.application.provider.model.TemperatureData;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-20T08:44:02.600773+02:00[Europe/Stockholm]")
@Controller
@RequestMapping("${openapi.temperatureSensorAPIHTTPSJSON.base-path:}")
public class TemperatureApiController implements TemperatureApi {

    private final NativeWebRequest request;

    @Autowired
    public TemperatureApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
