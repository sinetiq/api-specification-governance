package se.sinetiq.core.sr.consul.api;

import java.util.Objects;

/**
 * Identifiers for service types.
 */
public class ServiceType {

	private final String type;

	/**
	 * Create a new ServiceType.
	 * @param type the service type (e.g. "_http").
	 */
	public ServiceType(String type) {
		this.type = type;
	}

	/**
	 * Get the service type.
	 * @return the service type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns a string representation of the ServiceType.
	 * Examples: "_http", "_ftp".
	 * @return a string of the format "{type}[,{subtype}][,{subtype}][...]".
	 */
	@Override
	public String toString() {
        return type;
	}

	/**
	 * Returns a ServiceType object representing the type specified in the String.
	 * The argument is expected to be in the format returned by {@link #toString()}.
	 * @param s the string to be parsed.
	 * @return a ServiceType representing the type specified by the argument.
	 * @throws IllegalArgumentException if the string cannot be parsed as a ServiceType.
	 */
	public static ServiceType valueOf(String s) {
		return new ServiceType(s);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ServiceType other = (ServiceType) obj;
        return Objects.equals(this.type, other.type);
    }

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 29 * hash + (this.type != null ? this.type.hashCode() : 0);
		return hash;
	}
}
