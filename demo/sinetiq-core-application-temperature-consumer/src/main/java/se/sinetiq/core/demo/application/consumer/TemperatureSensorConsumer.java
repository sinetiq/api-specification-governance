package se.sinetiq.core.demo.application.consumer;

import java.util.List;

import se.sinetiq.core.demo.api.client.ApiClient;
import se.sinetiq.core.demo.api.client.ApiException;
import se.sinetiq.core.demo.api.client.Configuration;
import se.sinetiq.core.demo.api.client.model.TemperatureData;
import se.sinetiq.core.sr.consul.api.ConsulAPI;
import se.sinetiq.core.demo.api.client.api.DefaultApi;

import se.sinetiq.core.sr.consul.api.ServiceType;
import se.sinetiq.core.sr.consul.api.ServiceName;

public class TemperatureSensorConsumer {

  public static void main(String[] args) {

    ConsulAPI consulAPI = new ConsulAPI();
    //(environment.getProperty("sr.host"), environment.getProperty("sr.port")
    if(consulAPI.configure("localhost","8500") == false) {
      System.out.println("Service registry configuration failure!");
      return;
    }
    // Part 1: Discover (lookup) services (api instances) that fulfill the api specification 'temperature-sensor'
    ServiceType serviceType = new ServiceType("temperature-sensor-rest-json");
    List<ServiceName> apiInstances = consulAPI.getServiceInstances(serviceType);
    System.out.println("Found " + apiInstances.size() + " instaces.");
    for(ServiceName sn : apiInstances) {
      // Found service
      System.out.println("+ " + sn.getName());
    }

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
