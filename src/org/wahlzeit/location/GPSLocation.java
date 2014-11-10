package org.wahlzeit.location;


import com.mapcode.Mapcode;
import com.mapcode.MapcodeCodec;
import com.mapcode.Point;
import com.mapcode.UnknownMapcodeException;

public class GPSLocation extends AbstractLocation implements Location {
	
	private double latitude; 
	private double longitude;
	
	public GPSLocation(){
		this.latitude =  0.0;
		this.longitude = 0.0;
	}
	
	public GPSLocation(double latitude,double longitude){
		assertIsValidLocation(latitude, longitude);	
		initialize(latitude,longitude);
	}
	
	/**
	 * 
	 * @methodtype initialization
	 */
	protected void initialize(double latitude, double longitude){
		this.latitude= latitude;
		this.longitude = longitude;
	}
	

	@Override
	/**
	 * Sets the location(latitude, longitude).
	 * @methodtype set
	 */
	public void doSetLocation(double latitude, double longitude) {
		initialize(latitude,longitude);
	}

	
	/**
	 * @methodtype assertion 
	 */
	protected static void assertIsValidLocation(double latitude, double longitude) {
		if (latitude < -180 || latitude > 180) {
			throw new IllegalArgumentException("Not a valid latitude!");
		}
		if (longitude < -180 || longitude > 180) {
			throw new IllegalArgumentException("Not a valid latitude!");
		}
	}

	/**
	 * Gets the latitude of the location.
	 * @methodtype get
	 */
	public double getLatitude() {
		return this.latitude;
	}

	/**
	 * Gets the longitude of the location.
	 * @methodtype get
	 */
	public double getLongitude() {
		return this.longitude;
	}


	@Override
	/**
	 * Checks if the photo has an location.
	 * @methodtype boolean query method
	 */
	public boolean hasLocation() {
		if(this.latitude == 0.0 && this.longitude == 0.0)
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
		Point point;
		try {
			point = MapcodeCodec.decode(mapcode);
			this.latitude = point.getLatDeg();
			this.longitude = point.getLonDeg();
		}
		catch(UnknownMapcodeException e)
		{
			e.printStackTrace();
		}	
	}

	@Override
	/**
	 * Gets the mapcode of the location.
	 * @methodtype get
	 */
	public String getMapcode() {
		Mapcode mapcode = MapcodeCodec.encodeToInternational( this.latitude,this.longitude);
		return mapcode.toString();
		
	}

	@Override
	/**
	 * Returns a simple readable discription of the location.
	 * @return String
	 * @methodtype get
	 */
	public String asString() {
		return this.latitude+ " " +this.longitude  ;
	}

	@Override
	public boolean isEqual(double latitude, double longitude) {
		// TODO Auto-generated method stub
		return false;
	}

}
