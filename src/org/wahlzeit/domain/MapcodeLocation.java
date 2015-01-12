package org.wahlzeit.domain;

/**
 * Mapcode Location implementation. 
 *
 * @author Steffen Loskarn
 * @version 1.0
 * @date 07.11.2014
 *
 */

import java.util.List;

import com.mapcode.Mapcode;
import com.mapcode.MapcodeCodec;
import com.mapcode.Point;
import com.mapcode.Territory;
import com.mapcode.UnknownMapcodeException;

public class MapcodeLocation extends AbstractLocation implements Location {

	private double longitude;
	private double latitude;
	private String mapcode; // contains the mapcode information i.e. "NLD 49.v4"

	/**
	 * Initializes a new MapcodeLocation.
	 * 
	 * @methodtype constructor
	 * @param
	 */
	public MapcodeLocation() {
		this.mapcode = "";
		initialize(mapcode);
	}

	/**
	 * Initializes a new MapcodeLocation.
	 *
	 * @return MapcodeLocation
	 * @methodtype constructor
	 */
	public MapcodeLocation(String mapcode) {
		assertIsValidLocation(mapcode);
		this.mapcode = mapcode;
		initialize(mapcode);
	}

	/**
	 * 
	 * @methodtype initialization
	 */
	protected void initialize(String mapcode) {
		Point point;
		try {
			point = MapcodeCodec.decode(mapcode);
			this.latitude = point.getLatDeg();
			this.longitude = point.getLonDeg();
		} catch (UnknownMapcodeException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * Sets the location(latitude, longitude).
	 * @methodtype set
	 */
	public void doSetLocation(double latitude, double longitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	/**
	 * @methodtype assertion
	 */
	protected static void assertIsValidLocation(String mapcode) {
		if (mapcode == null) {
			throw new IllegalArgumentException("Not a valid location!");
		}
	}

	@Override
	/**
	 * Checks if the photo has an location.
	 *
	 * @return boolean
	 * @methodtype boolean query method
	 */
	public boolean hasLocation() {
		if (this.mapcode == "")
			return false;
		else
			return true;
	}

	@Override
	/**
	 * Sets the mapcode as location of a photo.
	 * @methodtype set
	 */
	public void setMapcode(String mapcode) {
		assertIsValidLocation(mapcode);
		this.mapcode = mapcode;
		Point point;
		try {
			point = MapcodeCodec.decode(mapcode);
			this.latitude = point.getLatDeg();
			this.longitude = point.getLonDeg();
		} catch (UnknownMapcodeException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * Gets the mapcode of the location.
	 *
	 * @return String
	 * @methodtype get
	 */
	public String getMapcode() {
		return mapcode;
	}

	@Override
	/**
	 * Returns a simple readable discription of the location.
	 * 
	 * @return String
	 * @methodtype get
	 */
	public String asString() {
		return mapcode;
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
	 *
	 * @return boolean
	 * @methodtype comparison
	 */
	public boolean isEqual(double latitude, double longitude) {
		if(latitude==this.latitude && longitude==this.longitude)
		return true;
		else
		return false;
	}

}