package org.domainzeit.model.location;

import org.domainzeit.exception.LocationException;

/**
 * The Location interface captures all methods available for GPSLocation, MapcodeLocations etc.
 * 
 * Location/Photo Collaboration: Location is the service
 * with the purpose to provide location functionality to the Photo class.
 *
 * @author Steffen Loskarn
 * @version 1.0
 * @date 07.11.2014
 *
 */

public interface Location {

	/**
	 * Gets the laitute of the location.
	 *
	 * @return double
	 * @methodtype get
	 */
	public double getLatitude();

	/**
	 * Gets the longitude of location.
	 *
	 * @return double
	 * @methodtype get
	 */
	public double getLongitude();

	/**
	 * Gets the mapcode of the location.
	 *
	 * @return String
	 * @methodtype get
	 */
	public String getMapcode();

	/**
	 * Sets the location.
	 * 
	 * @methodtype set
	 */
	public void setLocation(double[] location);

	/**
	 * Sets the mapcode as location of a photo.
	 * @throws LocationException 
	 * 
	 * @methodtype set
	 */
	public void setMapcode(String mapcode) throws LocationException;

	/**
	 * Sets the location(latitude, longitude).
	 * 
	 * @methodtype set
	 */
	public void setLocation(double latitude, double longitude);

	/**
	 * Removes the location from the photo.
	 * 
	 * @methodtype command method
	 */
	public void removeLocation();

	/**
	 * Checks if the photo has an location.
	 *
	 * @return boolean
	 * @methodtype boolean query method
	 */
	public boolean hasLocation();

	/**
	 * Returns a simple readable discription of the location.
	 * 
	 * @return String
	 * @methodtype conversion
	 */
	public String asString();

	/**
	 * Returns true if objects are equal. Objects may be different classes.
	 * 
	 * @param latitude, longitude
	 * @return boolean
	 */
	public boolean isEqual(double latitude, double longitude);
}
