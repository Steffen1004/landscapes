package org.wahlzeit.location;

public abstract class AbstractLocation implements Location {
	
	
	
	protected abstract void doSetLocation(double latitude, double longitude);
	
	/**
	 * Gets the location.
	 * @return 
	 * @methodtype get
	 */
	public abstract double[] getLocation();
	
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
	
	public double getLatitude()
	{
		double[] location = getLocation();
		return location[0];
	}

	public double getLongitude()
	{
		double[] location = getLocation();
		return location[1];
	}
	
	/**
	 * Sets the location.
	 * @methodtype set
	 */
	public void setLocation(double latitude, double longitude) {
		doSetLocation(latitude, longitude);
	}
	


	/**
	 * Sets the location.
	 * @methodtype set
	 */
	public void setLocation(double[] location) {
		doSetLocation(location[0],location[1]);
	}
	
	
	public void removeLocation() {
	}
	
	public void hasLocation(Location location) {
	}
	
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
	public boolean isEqual(Location location) {
		return false;
	}

}
