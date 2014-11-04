package org.wahlzeit.location;

import java.awt.Image;

public class GPSLocation extends AbstractLocation {
	
	protected Location location;
	
	public GPSLocation(){
		this.location = new GPSLocation();
	}
	
	public GPSLocation(Location location){
		assertIsValidLocation(location);
		this.location = location;	
	}
	

	@Override
	public void setLocation(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getLocation() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @methodtype assertion 
	 */
	protected static void assertIsValidLocation(Location location) {
		if (location == null) {
			throw new IllegalArgumentException("Not a valid location!");
		}
	}




}
