package org.wahlzeit.location;

public abstract class AbstractLocation implements Location {
	
	/**
	 * Gets the location.
	 * @methodtype get
	 */
	public void getLocation() {
	}
	
	/**
	 * Sets the location.
	 * @methodtype set
	 */
	public void setLocation() throws InvalidLocationException {
	}
	
	
	public void removeLocation() throws InvalidLocationException {
	}
	
	public void hasLocation(Location location) throws InvalidLocationException {
	}
	
	/**
	 * Returns a simple readable discription of the location.
	 * @return String
	 * @methodtype get
	 */
	public String asString() {
		return null;
	}
	
	/**
	 * Returns true if objects are equal.
	 * Objects may be different classes.
	 * @param location
	 * @return boolean
	 */
	public boolean isEqual(Location location) {
		return false;
	}

}
