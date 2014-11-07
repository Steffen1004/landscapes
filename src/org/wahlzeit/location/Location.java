package org.wahlzeit.location;

public interface Location {
	/**
	 * Gets the location.
	 * @methodtype get
	 */
	public double[] getLocation();
	
	/**
	 * Gets the laitute of the location.
	 * @methodtype get
	 */
	public double getLatitude();
	
	/**
	 * Gets the longitude of location.
	 * @methodtype get
	 */
	public double getLongitude();
	
	/**
	 * Gets the mapcode of the location.
	 * @methodtype get
	 */
	public String getMapcode();
	
	
	/**
	 * Sets the location.
	 * @methodtype set
	 */
	public void setLocation(double[] location);
	
	/**
	 * Sets the mapcode as location of a photo.
	 * @methodtype set
	 */
	public void setMapcode(String mapcode);
	
	public void setLocation(double latitude, double longitude);
	/**
	 * Removes the location from the photo.
	 * @methodtype command method
	 */
	public void removeLocation();
	
	/**
	 * Checks if the photo has an location.
	 * @methodtype boolean query method
	 */
	public boolean hasLocation();
	
	/**
	 * Returns a simple readable discription of the location.
	 * @return String
	 * @methodtype conversion
	 */
	public String asString();
	
	/**
	 * Returns true if objects are equal.
	 * Objects may be different classes.
	 * @param location
	 * @return boolean
	 */
	public boolean isEqual(Location location);

	
	
	


}