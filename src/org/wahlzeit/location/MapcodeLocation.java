package org.wahlzeit.location;

import java.util.List;

import com.mapcode.Mapcode;
import com.mapcode.MapcodeCodec;
import com.mapcode.Point;
import com.mapcode.Territory;
import com.mapcode.UnknownMapcodeException;

public class MapcodeLocation extends AbstractLocation implements Location{
	
	private double longitude;
	private double latitude;
	private String mapcode; //contains the mapcode information i.e. "NLD 49.v4"

	
	public MapcodeLocation(){
		this.mapcode="";
	}
	
	public MapcodeLocation(String mapcode){
		assertIsValidLocation(mapcode);
		this.mapcode=mapcode;
	}

	@Override
	public void doSetLocation(double latitude, double longitude) {
		this.longitude= latitude;
		this.latitude = longitude;
	}

	@Override
	public double[] getLocation() {
		Point point;
		double[] result = new double[2];
		
		try {
			point = MapcodeCodec.decode(mapcode);
			result = new double[]{point.getLonDeg(), point.getLatDeg()};
		}
		catch(UnknownMapcodeException e){
			e.printStackTrace();
		}
		return result;	
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
	public boolean hasLocation() {	
//		if(this.location == null)
//			return false;
//		else 
//			return true;
		return true;
	}

	@Override
	public void setMapcode(String mapcode) {
		//mapcode encoden, wenn funct, dann keine exeption
		this.mapcode = mapcode;
	}

	@Override
	public String getMapcode() {
		return mapcode;
	}

	@Override
	public String asString() {
		return mapcode;
	}
	
}