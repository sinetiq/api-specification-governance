package se.sinetiq.core.demo.application.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Units
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-20T08:44:02.600773+02:00[Europe/Stockholm]")
public class Units {

  @Valid
  private List<String> units;

  public Units units(List<String> units) {
    this.units = units;
    return this;
  }

  public Units addUnitsItem(String unitsItem) {
    if (this.units == null) {
      this.units = new ArrayList<>();
    }
    this.units.add(unitsItem);
    return this;
  }

  /**
   * Get units
   * @return units
  */
  
  @Schema(name = "units", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("units")
  public List<String> getUnits() {
    return units;
  }

  public void setUnits(List<String> units) {
    this.units = units;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Units units = (Units) o;
    return Objects.equals(this.units, units.units);
  }

  @Override
  public int hashCode() {
    return Objects.hash(units);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Units {\n");
    sb.append("    units: ").append(toIndentedString(units)).append("\n");
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

