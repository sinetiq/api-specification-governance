package se.sinetiq.core.sr.consul.api;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.ConsulException;
import com.orbitz.consul.NotRegisteredException;
import com.orbitz.consul.model.ConsulResponse;
import com.orbitz.consul.model.agent.FullService;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import com.orbitz.consul.model.health.Service;
import com.orbitz.consul.option.QueryOptions;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sinetiq
 */
public class ConsulAPI {

    private static final Logger LOG = Logger.getLogger(ConsulAPI.class.getName());

    private AgentClient agentClient;

    public ConsulAPI() { }

    public void configure(String serverIP, String serverPort) throws ConsulException {
        if (serverIP.isEmpty() || serverPort.isEmpty()) {
            throw new IllegalArgumentException(String.format("serverIP: %s, serverPort: %s", serverIP, serverPort));
        }

        URL url = null;
        try {
            if (serverPort.equalsIgnoreCase("443")) {
                url = new URI("https", serverIP, serverPort).toURL();
            } else {
                url = new URI("http", serverIP, serverPort).toURL();
            }
        } catch (URISyntaxException | MalformedURLException e) {
            throw new RuntimeException("Error creating URL from configuration parameters", e);
        }

        LOG.log(Level.INFO, "Using service registry at " + url);

        int retry_limit = 3;
        Consul client = null;
        for (int i = 1; i <= retry_limit; i++) {
            try {
                client = Consul.builder().withUrl(url).build();
                break;
            } catch (ConsulException ce) {
                LOG.log(Level.WARNING, String.format("Consul exception (%s), retrying (%d/%d)",
                        ce.getMessage(), i, retry_limit));
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (client == null) {
            throw new RuntimeException("Gave up connecting to Consul due to previous errors"); // TODO: propagate better
        }
        agentClient = client.agentClient();
    }


    public List<ServiceName> getServiceInstances(ServiceType type) {
        LOG.log(Level.INFO, "Looking up " + type);
        List<ServiceName> results = new ArrayList<ServiceName>();
        for (ServiceData data : getAllServices()) {
            LOG.log(Level.INFO, "Service " + data.getName().getName());
            ServiceName serviceName = data.getName();
            LOG.log(Level.INFO, "type: " + type.getType() + " cmp: " + serviceName.getType().getType());
            if(serviceName.getType().equals(type)){
                LOG.log(Level.INFO, "Add service " + data.getName().getName());
                results.add(serviceName);
            }
        }
        return results;
    }

    public ServiceData getServiceData(ServiceName serviceName) {
        for (ServiceData data : getAllServices()) {
          try {
            LOG.log(Level.INFO, "getServiceData: name = " + data.getName().getName() + " , sn = " + serviceName.getName());
            ConsulResponse<FullService> serviceData = agentClient.getService(data.getName().getName(), QueryOptions.BLANK);                
            if (serviceName.getName().equals(data.getName().getName())) {
                return data;
            }
          } catch (Exception e) {
            LOG.log(Level.SEVERE, "Failed to get needed service information for {0}", new Object[]{e.getMessage()});            
          }
        }
        LOG.log(Level.INFO, "getServiceData: ServiceData = null");
        return null;
    }

    public void registerService(ServiceData data) throws NotRegisteredException {
        ConsulResponse<FullService> service = null;
        String id;
        String name;
        Registration newService;

        id = data.getName().getName();
        try {
            service = agentClient.getService(id, QueryOptions.BLANK);
        } catch (NotRegisteredException e) {
            LOG.log(Level.INFO, "Consul not registered service {0}", id);

        }

        name = data.getName().getType().getType();
        LOG.log(Level.INFO, "Consul service name = {0}", name);

        if (service == null) {
            newService = ImmutableRegistration.builder()
                    .id(id)
                    .name(name)
                    .address(data.getHost())
                    .port(data.getPort())
                    .meta(data.getProperties())
                    .build();

            agentClient.register(newService);
        }
    }

    public void unregisterService(ServiceName serviceName){ //throws ServiceRegisterException {
        try {
            agentClient.deregister(serviceName.getName());
            LOG.log(Level.INFO, "Consul unregistered service {0}", "id");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Consul failed to unregister service {0}", "id");
            //throw new ServiceRegisterException(e.getMessage());
        }
    }

    public ServiceName makeServiceName(String name, ServiceType type) {
        return new ServiceName(name, type);
    }

    private List<ServiceData> getAllServices() {
        List<ServiceData> results = new ArrayList<ServiceData>();
        Map<String, Service> services = agentClient.getServices();
        for (Service service : services.values()) {
            LOG.log(Level.INFO, "Found service: " + service.getId());
            try {
                ServiceData serviceData = new ServiceData();                
                serviceData.setHost(service.getAddress());                
                serviceData.setPort(service.getPort());
                LOG.log(Level.INFO, "getAllServices: serviceData.setName(ServiceName.valueOf [service.getId()] = " + service.getId() + " , type = " + service.getService());
                serviceData.setName(new ServiceName(service.getId(), new ServiceType(service.getService())));
                serviceData.getProperties().putAll(service.getMeta());
                LOG.log(Level.INFO, "SERVICE DATA : " + serviceData.toString());
                // service.getTags(); // TODO: use this?
                results.add(serviceData);
            } catch (TypeNotPresentException ex) {
                LOG.log(Level.INFO, "TypeNotPresentException service: " + service.getId());
            } catch(Exception e) {
                LOG.log(Level.INFO, "getAllServices service: " + service.getId());
                e.printStackTrace();
            }
        }
        return results;
    }
}
