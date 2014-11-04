package org.wahlzeit.location;

public class MapcodeLocation extends AbstractLocation{
	
	protected Location location;
	
	public MapcodeLocation(){
		this.location = new MapcodeLocation();
	}
	
	public MapcodeLocation(Location location){
		assertIsValidLocation(location);
		this.location=location;
	}

	@Override
	public void setLocation(Location location) {
		this.location = location;
		
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