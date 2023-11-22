package se.sinetiq.core.demo.application.consumer;

import se.sinetiq.core.demo.api.client.ApiClient;
import se.sinetiq.core.demo.api.client.ApiException;
import se.sinetiq.core.demo.api.client.Configuration;
import se.sinetiq.core.demo.api.client.model.TemperatureData;
import se.sinetiq.core.demo.api.client.api.DefaultApi;

public class TemperatureSensorConsumer {

  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://127.0.0.1:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    try {
      TemperatureData result = apiInstance.temperatureGet();
      System.out.println(result);

    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#temperatureGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      /*e.printStackTrace();*/
    }
  }
  
}
