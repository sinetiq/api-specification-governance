package se.sinetiq.core.demo.application.provider.api;

import org.springframework.format.annotation.DateTimeFormat;
import se.sinetiq.core.demo.application.provider.model.HistoricalTemperatureData;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

import java.util.*;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-20T08:44:02.600773+02:00[Europe/Stockholm]")
@Controller
@RequestMapping("${openapi.temperatureSensorAPIHTTPSJSON.base-path:}")
public class TemperatureApiController implements TemperatureApi {
    TreeMap<OffsetDateTime, TemperatureData> tempHistory = new TreeMap<>();

    @Override
    public ResponseEntity<TemperatureData> temperatureGet() {
        BigDecimal temp = new BigDecimal(Math.random() * 2.0 + 10.0);
        TemperatureData data = new TemperatureData()
                .unit("C")
                .machineID("Thermometer 1")
                .location("Site 1")
                .timestamp(OffsetDateTime.now())
                .temperature(temp.setScale(2, RoundingMode.DOWN));
        tempHistory.put(data.getTimestamp(), data);
        return ResponseEntity.ok(data);
    }

    @Override
    public ResponseEntity<HistoricalTemperatureData> temperatureHistoryGet(OffsetDateTime startDate, OffsetDateTime endDate) {
        HistoricalTemperatureData data = new HistoricalTemperatureData();
        NavigableMap<OffsetDateTime, TemperatureData> subMap = tempHistory.subMap(startDate, true, endDate, true);
        data.setReadings(new LinkedList<>(subMap.values()));
        return ResponseEntity.ok(data);
    }
}
