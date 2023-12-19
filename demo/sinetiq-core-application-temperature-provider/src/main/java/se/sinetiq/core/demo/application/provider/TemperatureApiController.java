package se.sinetiq.core.demo.application.provider;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import se.sinetiq.core.demo.application.provider.api.TemperatureApi;
import se.sinetiq.core.demo.application.provider.model.HistoricalTemperatureData;
import se.sinetiq.core.demo.application.provider.model.TemperatureData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.NavigableMap;
import java.util.TreeMap;

@Controller
@RequestMapping
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
