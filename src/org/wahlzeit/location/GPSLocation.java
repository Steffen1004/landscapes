package org.wahlzeit.location;

import java.awt.Image;

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
	
	public GPSLocation(double latitude, double longitude){
//		assertIsValidLocation(location);	
		this.latitude= latitude;
		this.longitude = longitude;
	}
	

	@Override
	public void doSetLocation(double latitude, double longitude) {
		this.longitude= longitude;
		this.latitude = latitude;
	}

	
	/**
	 * @methodtype assertion 
	 */
//	protected static void assertIsValidLocation(double[] location) {
//		if (location == null) {
//			throw new IllegalArgumentException("Not a valid location!");
//		}
//	}

	public double getLatitude() {
		return this.latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	@Override
	public double[] getLocation() {
		return new double[]{this.latitude,this.longitude};
	}

	@Override
	public boolean hasLocation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMapcode(String mapcode) {
		Point point;
		try {
			point = MapcodeCodec.decode(mapcode);
//			this.location = new double[]{point.getLonDeg(), point.getLatDeg()};	
			this.latitude = point.getLatDeg();
			this.longitude = point.getLonDeg();
		}
		catch(UnknownMapcodeException e)
		{
			e.printStackTrace();
		}	
	}

	@Override
	public String getMapcode() {
		Mapcode mapcode = MapcodeCodec.encodeToInternational(this.latitude,this.longitude);
		return mapcode.toString();
		
	}

	@Override
	public String asString() {
		return this.latitude + "" + this.longitude;
	}

}
