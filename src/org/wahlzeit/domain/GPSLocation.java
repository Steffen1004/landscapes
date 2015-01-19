package org.wahlzeit.domain;

/**
 * GPSLocation implementation. 
 *
 * @author Steffen Loskarn
 * @version 1.0
 * @date 07.11.2014
 *
 */

import org.wahlzeit.services.Log;
import org.wahlzeit.services.SysLog;

import com.mapcode.Mapcode;
import com.mapcode.MapcodeCodec;
import com.mapcode.Point;
import com.mapcode.UnknownMapcodeException;

public class GPSLocation extends AbstractLocation implements Location {

	private double latitude;
	private double longitude;

	/**
	 * Initializes a new GPSLocation.
	 * 
	 * @methodtype constructor
	 * @param
	 */
	public GPSLocation() {
		this.latitude = 0.0;
		this.longitude = 0.0;
	}

	/**
	 * Initializes a new GPSLocation.
	 * 
	 * @methodtype constructor
	 * @param double latitude double longitude
	 * @throws LocationException 
	 */
	public GPSLocation(double latitude, double longitude) throws LocationException {
		assertIsValidLocation(latitude, longitude);
		initialize(latitude, longitude);
	}

	/**
	 * 
	 * @methodtype initialization
	 */
	protected void initialize(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @throws LocationException 
	 * @methodtype assertion
	 */
	protected static void assertIsValidLocation(double latitude,
			double longitude) throws LocationException {
		if (latitude < -90 || latitude > 90) {
			throw new LocationException("Not a valid latitude! Range 90° - (-90°)");
		}
		if (longitude < -180 || longitude > 180) {
			throw new LocationException("Not a valid longitude! Range 180° - (-180°)");
		}
	}

	@Override
	/**
	 * Sets the location(latitude, longitude).
	 * @methodtype set
	 */
	public void doSetLocation(double latitude, double longitude) {
		initialize(latitude, longitude);
	}

	/**
	 * Gets the latitude of the location.
	 *
	 * @return double
	 * @methodtype get
	 */
	public double getLatitude() {
		return this.latitude;
	}

	/**
	 * Gets the longitude of the location.
	 *
	 * @return double 
	 * @methodtype get
	 */
	public double getLongitude() {
		return this.longitude;
	}

	@Override
	/**
	 * Checks if the photo has an location.
	 * 
	 * @return double 
	 * @methodtype boolean query method
	 */
	public boolean hasLocation() {
		if (this.latitude == 0.0 && this.longitude == 0.0)
			return false;
		else
			return true;
	}

	@Override
	/**
	 * Gets the mapcode of the location.
	 * 
	 * @return String 
	 * @methodtype get
	 */
	public String getMapcode() {
		Mapcode mapcode = MapcodeCodec.encodeToInternational(this.latitude,
				this.longitude);
		return mapcode.toString();
	}

	@Override
	/**
	 * Sets the mapcode as location of a photo.
	 * @methodtype set
	 */
	public void setMapcode(String mapcode) throws LocationException {
		Point point;
		try {
			point = MapcodeCodec.decode(mapcode);
			this.latitude = point.getLatDeg();
			this.longitude = point.getLonDeg();
		} catch (UnknownMapcodeException e) {
			e.printStackTrace();
			throw new LocationException("Mapcode couldn't be converted to GPS coordinates");
		}
	}

	@Override
	/**
	 * Returns a simple readable discription of the location.
	 * 
	 * @return String
	 * @methodtype get
	 */
	public String asString() {
		return this.latitude + " " + this.longitude;
	}

	@Override
	/**
	 * Checks if the photo has an location.
	 *
	 * @return boolean 
	 * @methodtype boolean query method
	 */
	public boolean isEqual(double latitude, double longitude) {
		if (this.latitude == latitude && this.longitude == longitude)
			return true;
		else
			return false;
	}

}
