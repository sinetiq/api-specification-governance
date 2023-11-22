package se.sinetiq.core.demo.application.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * TemperatureData
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-20T08:44:02.600773+02:00[Europe/Stockholm]")
public class TemperatureData {

  private BigDecimal temperature;

  private String unit;

  private String location;

  private String machineID;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime timestamp;

  public TemperatureData temperature(BigDecimal temperature) {
    this.temperature = temperature;
    return this;
  }

  /**
   * Get temperature
   * @return temperature
  */
  @Valid 
  @Schema(name = "temperature", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("temperature")
  public BigDecimal getTemperature() {
    return temperature;
  }

  public void setTemperature(BigDecimal temperature) {
    this.temperature = temperature;
  }

  public TemperatureData unit(String unit) {
    this.unit = unit;
    return this;
  }

  /**
   * Get unit
   * @return unit
  */
  
  @Schema(name = "unit", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("unit")
  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public TemperatureData location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  */
  
  @Schema(name = "location", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("location")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public TemperatureData machineID(String machineID) {
    this.machineID = machineID;
    return this;
  }

  /**
   * Get machineID
   * @return machineID
  */
  
  @Schema(name = "machineID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("machineID")
  public String getMachineID() {
    return machineID;
  }

  public void setMachineID(String machineID) {
    this.machineID = machineID;
  }

  public TemperatureData timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
  */
  @Valid 
  @Schema(name = "timestamp", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("timestamp")
  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemperatureData temperatureData = (TemperatureData) o;
    return Objects.equals(this.temperature, temperatureData.temperature) &&
        Objects.equals(this.unit, temperatureData.unit) &&
        Objects.equals(this.location, temperatureData.location) &&
        Objects.equals(this.machineID, temperatureData.machineID) &&
        Objects.equals(this.timestamp, temperatureData.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(temperature, unit, location, machineID, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TemperatureData {\n");
    sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    machineID: ").append(toIndentedString(machineID)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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

