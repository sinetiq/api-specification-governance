package se.sinetiq.core.sr.consul.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Data about a service instance.
 */
public class ServiceData {

	private ServiceName name;
	private String host;
	private int port;
	private Map<String, String> properties = new HashMap<String, String>();

	/**
	 * Create a ServiceData object.
	 */
	public ServiceData() {
	}

	/**
	 * Create a ServiceData object.
	 * @param name the name of the service.
	 * @param host the fully qualified name of the host providing the service.
	 * @param port the TCP or UDP port number of the service.
	 */
	public ServiceData(ServiceName name, String host, int port) {
		this.name = name;
		this.host = host;
		this.port = port;
	}

	/**
	 * Get the name of the service.
	 * @return the service name.
	 */
	public ServiceName getName() {
		return name;
	}

	/**
	 * Set the name of the service.
	 * @param name the new service name.
	 */
	public void setName(ServiceName name) {
		this.name = name;
	}

	/**
	 * Get the fully qualified name of the host providing the service.
	 * @return the host name.
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Set the fully qualified name of the host providing the service.
	 * @param host the new host name.
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Get the TCP or UDP port number.
	 * Whether it is UDP or TCP depends on the service type.
	 * @return the port number.
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Set the TCP or UDP port number.
	 * @param port the new port number.
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Get the property map.
	 * The returned map can be modified to add, change and remove key-value pairs.
	 * @return a key-value mapping.
	 */
	public Map<String, String> getProperties() {
		return properties;
	}

	@Override
	public String toString() {
		return String.format("%s: %s:%d %s", name, host, port, properties);
	}

}
