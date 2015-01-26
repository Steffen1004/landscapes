package org.domainzeit.exception;

/**
 * LocationException extends Exception.
 *
 * @author Steffen Loskarn
 * @version 1.0
 * @date 16.01.2015
 *
 */

public class LocationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocationException() {
		super();
	}

	public LocationException(String message) {
		super(message);
	}

	public LocationException(Throwable cause) {
		super(cause);
	}

	public LocationException(String message, Throwable cause) {
		super(message, cause);
	}
}