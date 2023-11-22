package se.sinetiq.core.demo.application.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import se.sinetiq.core.demo.application.provider.model.TemperatureData;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * HistoricalTemperatureData
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-20T08:44:02.600773+02:00[Europe/Stockholm]")
public class HistoricalTemperatureData {

  @Valid
  private List<@Valid TemperatureData> readings;

  public HistoricalTemperatureData readings(List<@Valid TemperatureData> readings) {
    this.readings = readings;
    return this;
  }

  public HistoricalTemperatureData addReadingsItem(TemperatureData readingsItem) {
    if (this.readings == null) {
      this.readings = new ArrayList<>();
    }
    this.readings.add(readingsItem);
    return this;
  }

  /**
   * Get readings
   * @return readings
  */
  @Valid 
  @Schema(name = "readings", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("readings")
  public List<@Valid TemperatureData> getReadings() {
    return readings;
  }

  public void setReadings(List<@Valid TemperatureData> readings) {
    this.readings = readings;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HistoricalTemperatureData historicalTemperatureData = (HistoricalTemperatureData) o;
    return Objects.equals(this.readings, historicalTemperatureData.readings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(readings);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HistoricalTemperatureData {\n");
    sb.append("    readings: ").append(toIndentedString(readings)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

