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
import se.sinetiq.core.sr.consul.api.ServiceData;

public class TemperatureSensorConsumer {

    public static void main(String[] args) {

        String host = System.getenv("CFG_SR_HOST");
        String port = System.getenv("CFG_SR_PORT");
        ConsulAPI consulAPI = new ConsulAPI();
        try {
            consulAPI.configure(host, port);
        } catch (Throwable t) {
            System.out.println("Service registry configuration failure!");
            t.printStackTrace();
            System.exit(1);
        }

        ServiceData sd = lookupTempService(consulAPI);

        ApiClient defaultClient = Configuration.getDefaultApiClient();
        String pathStr = sd.getProperties().get("path") != null ? sd.getProperties().get("path") : "";
        defaultClient.setBasePath("http://" + sd.getHost() + ":" + sd.getPort() /*+ pathStr*/);

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        try {
            TemperatureData result = apiInstance.temperatureGet();
            System.out.println("Response from " + sd.getName() + ":");
            System.out.println(result);

        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#temperatureGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            /*e.printStackTrace();*/
        }
    }

    static ServiceData lookupTempService(ConsulAPI consulAPI) {
        String service = System.getenv("CFG_SERVICE_NAME");
        ServiceType serviceType = new ServiceType(service);

        int retry_limit = 10;
        for (int i = 1; i <= retry_limit; i++) {
            System.out.println("Attempting to call service (" + i + "/" + retry_limit + ")");
            //
            // Part 1: Discover (lookup) services (api instances) that fulfill the api specification 'temperature-sensor'
            //
            List<ServiceName> apiInstances = consulAPI.getServiceInstances(serviceType);
            System.out.println("Found " + apiInstances.size() + " instances.");
            for (ServiceName sn : apiInstances) {
                System.out.println("+ " + sn.getName());
            }

            if (apiInstances.size() > 0) {
                //
                // Part 2: Use discovery endpoint details for establish connection
                //
                ServiceName sn = apiInstances.get(0); // Just pick the first service (api instance) found
                ServiceData sd = consulAPI.getServiceData(sn);
                if (sd == null) {
                    throw new RuntimeException("No services data found");
                }

                return sd;
            }

            // Else, wait a while and retry.
            try {
                Thread.sleep(5000);
                continue;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("No service found after " + retry_limit + " tries, giving up");
    }
}
