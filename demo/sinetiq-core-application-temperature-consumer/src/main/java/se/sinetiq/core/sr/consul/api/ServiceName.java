package se.sinetiq.core.sr.consul.api;

import java.util.Objects;

/**
 * A unique identifier for a service instance.
 */
public class ServiceName {

	private final String name;
	private final ServiceType type;

	/**
	 * Create a new ServiceName.
	 * @param name the name of the service.
	 * @param type the type of service.
	 */
	public ServiceName(String name, ServiceType type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Get the service name.
	 * @return the service name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the service type.
	 * @return the service type.
	 */
	public ServiceType getType() {
		return type;
	}

	/**
	 * Returns a ServiceName object representing the service specified in the String.
	 * The argument is expected to be in the format returned by {@link #toString()}.
	 * @param s the string to be parsed.
	 * @return a ServiceName representing the service specified by the argument.
	 * @throws IllegalArgumentException if the string cannot be parsed as a ServiceName.
	 */
	public static ServiceName valueOf(String s) {
		int i = indexOfNonEscaped(s, '.');
		if (i < 0) {
			throw new IllegalArgumentException("No '.' in service name: " + s);
		}
	
		String[] nameType = s.split("[.]");
		String name = nameType[0];
		ServiceType type = ServiceType.valueOf(nameType[1]);

		return new ServiceName(name, type);
	}

	@Override
	public String toString() {
		return name+"."+type.getType();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ServiceName other = (ServiceName) obj;
		if (!Objects.equals(this.name, other.name)) {
			return false;
		}
        return Objects.equals(this.type, other.type);
    }

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
		hash = 89 * hash + (this.type != null ? this.type.hashCode() : 0);
		return hash;
	}

	/**
	 * Find the first non-escaped occurrence of a character in a string.
	 * @see String#indexOf(int)
	 * @param string the string to look through.
	 * @param ch the character to find.
	 * @return the index of the first occurrence, or -1 if it can't be found. 
	 */
	private static int indexOfNonEscaped(String string, char ch) {
		for (int i = 0; i < string.length(); i++) {
			int c = string.charAt(i);
			if (c == '\\') {
				i++;
			} else if (c == ch) {
				return i;
			}
		}
		return -1;
	}

}
