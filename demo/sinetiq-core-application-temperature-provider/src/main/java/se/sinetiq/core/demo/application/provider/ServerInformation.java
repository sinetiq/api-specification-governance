package se.sinetiq.core.demo.application.provider;

import com.orbitz.consul.NotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import se.sinetiq.core.sr.consul.api.ConsulAPI;
import se.sinetiq.core.sr.consul.api.ServiceData;

@Component
public class ServerInformation {
    private final ServiceRegistryProperties serviceRegistryProperties;
    private final ServiceProperties serviceProperties;


    public ServerInformation(ServiceRegistryProperties serviceRegistryProperties,
                             ServiceProperties serviceProperties) {
        this.serviceRegistryProperties = serviceRegistryProperties;
        this.serviceProperties = serviceProperties;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onContextRefresh() {
        ConsulAPI consulAPI = getAPI();
        ServiceData sd = new ServiceData();
        sd.setHost(serviceProperties.getAdvertisedAddress());
        sd.setPort(serviceProperties.getAdvertisedPort());
        sd.setName(serviceProperties.getServiceName());
        sd.getProperties().put("path", serviceProperties.getBasePath());
        try {
            consulAPI.registerService(sd);
        } catch (NotRegisteredException e) {
            e.printStackTrace(); // TODO: Log
        }
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        ConsulAPI consulAPI = getAPI();
        consulAPI.unregisterService(serviceProperties.getServiceName());
    }

    private ConsulAPI getAPI() {
        ConsulAPI consulAPI = new ConsulAPI();
        try {
            consulAPI.configure(serviceRegistryProperties.getAddress(), serviceRegistryProperties.getPort() + ""); // TODO: Migrate to int
            return consulAPI;
        } catch (Throwable t) {
            throw new IllegalStateException("Service registry configuration failure!", t);
        }
    }
}
