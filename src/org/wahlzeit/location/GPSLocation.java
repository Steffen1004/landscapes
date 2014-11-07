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
	
	public GPSLocation(double longitude, double latitude){
//		assertIsValidLocation(location);	
		this.latitude= latitude;
		this.longitude = longitude;
	}
	

	@Override
	public void doSetLocation( double longitude, double latitude) {
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
		return new double[]{this.longitude, this.latitude};
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
		Mapcode mapcode = MapcodeCodec.encodeToInternational(this.longitude, this.latitude);
		return mapcode.toString();
		
	}

	@Override
	public String asString() {
		return this.longitude + " " +this.latitude ;
	}

}
