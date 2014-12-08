package org.wahlzeit.domain;

/**
 * Abstract class Location which implements the interface location.
 *
 * @author Steffen Loskarn
 * @version 1.0
 * @date 07.11.2014
 *
 */

public abstract class AbstractLocation implements Location {
	
	/**
	 * Sets the mapcode as location of a photo.
	 * @methodtype set
	 */
	public abstract void setMapcode(String mapcode);
	
	/**
	 * Gets the mapcode of the location.
	 * @methodtype get
	 */
	public abstract String getMapcode();
	
	/**
	 * Gets the latitude of the location.
	 * @methodtype get
	 */
	public abstract double getLatitude();

	/**
	 * Gets the longitude of the location.
	 * @methodtype get
	 */
	public abstract double getLongitude();

	/**
	 * Sets the location.
	 * @methodtype set
	 */
	public void setLocation(double latitude, double longitude) {
		doSetLocation(latitude, longitude);
	}
	
	/**
	 * Sets the location(latitude, longitude).
	 * @methodtype set
	 */
	protected abstract void doSetLocation(double latitude, double longitude);

	/**
	 * Sets the location.
	 * @methodtype set
	 */
	public void setLocation(double[] location) {
		doSetLocation(location[0],location[1]);
	}
	
	/**
	 * Removes the location from the photo.
	 * @methodtype command method
	 */
	public void removeLocation() {
	}
	
	/**
	 * Checks if the photo has an location.
	 * @methodtype boolean query method
	 */
	public abstract boolean hasLocation();
	
	/**
	 * Returns a simple readable discription of the location.
	 * @return String
	 * @methodtype get
	 */
	public abstract String asString();
	
	/**
	 * Returns true if objects are equal.
	 * Objects may be different classes.
	 * @param location
	 * @return boolean
	 */
	public abstract boolean isEqual(double latitude, double longitude);

}
