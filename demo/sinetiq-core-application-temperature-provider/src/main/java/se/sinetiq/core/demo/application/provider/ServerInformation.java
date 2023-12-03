package se.sinetiq.core.demo.application.provider;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

import se.sinetiq.core.sr.consul.api.ConsulAPI;
import se.sinetiq.core.sr.consul.api.ServiceData;
import se.sinetiq.core.sr.consul.api.ServiceType;
import se.sinetiq.core.sr.consul.api.ServiceName;

@Component
public class ServerInformation {
    @Autowired
    private Environment environment;

    @EventListener(ContextRefreshedEvent.class)
    public void onContextRefresh() throws Exception {
        String host = getHost();
        int port = getPort();

        ConsulAPI consulAPI = new ConsulAPI();
        try {
            consulAPI.configure(environment.getProperty("sr.host"), environment.getProperty("sr.port"));
        } catch (Throwable t) {
            throw new Exception("Service registry configuration failure!", t);
        }
        ServiceData sd = new ServiceData();
        sd.setHost(host);
        sd.setPort(port);
        String serviceName = environment.getProperty("service.name");
        String serviceType = environment.getProperty("service.type");
        sd.setName(new ServiceName(serviceName, new ServiceType(serviceType)));
        String basePath = environment.getProperty("service.basePath");
        sd.getProperties().put("path", basePath);
        consulAPI.registerService(sd);
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) throws Exception {
        ConsulAPI consulAPI = new ConsulAPI();
        try {
            consulAPI.configure(environment.getProperty("sr.host"), environment.getProperty("sr.port"));
        } catch (Throwable t) {
            throw new Exception("Service registry configuration failure!", t);
        }
        String serviceName = environment.getProperty("service.name");
        String serviceType = environment.getProperty("service.type");
        consulAPI.unregisterService(new ServiceName(serviceName, new ServiceType(serviceType)));
    }

    public String getHost() {
        String host = environment.getProperty("spring.datasource.url");
        if(host == null) {
            host = environment.getProperty("server.host");
        }
        if(host == null) {
            host = getHostAddress();
        }
        return host;
    }

    public int getPort() {
        String serverPort = environment.getProperty("server.port");
        return serverPort != null ? Integer.parseInt(serverPort) : 80;
    }

    private String getHostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "Unknown address";
        }
    }
}
