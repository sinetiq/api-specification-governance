package se.sinetiq.core.demo.application.provider;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import se.sinetiq.core.sr.consul.api.ServiceName;
import se.sinetiq.core.sr.consul.api.ServiceType;

@Configuration
@ConfigurationProperties(prefix = "service")
public class ServiceProperties {
    private String name;
    private String type;
    private String basePath;
    public String getBasePath() {
        return basePath;
    }

    private String advertisedAddress;
    private int advertisedPort;

    public ServiceName getServiceName() {
        return new ServiceName(name, new ServiceType(type));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getAdvertisedAddress() {
        return advertisedAddress;
    }

    public void setAdvertisedAddress(String advertisedAddress) {
        this.advertisedAddress = advertisedAddress;
    }

    public int getAdvertisedPort() {
        return advertisedPort;
    }

    public void setAdvertisedPort(int advertisedPort) {
        this.advertisedPort = advertisedPort;
    }
}
