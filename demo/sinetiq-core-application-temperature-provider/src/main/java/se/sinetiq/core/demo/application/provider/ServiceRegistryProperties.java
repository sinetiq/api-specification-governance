package se.sinetiq.core.demo.application.provider;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sr")
public class ServiceRegistryProperties {
    private String address;
    private int port;

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
