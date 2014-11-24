package org.wahlzeit.domain;

import org.wahlzeit.domain.GPSLocation;
import org.wahlzeit.domain.Location;
import org.wahlzeit.domain.MapcodeLocation;

import com.mapcode.MapcodeCodec;

import junit.framework.TestCase;
import static org.junit.Assert.*;

public class AbstractLocationTest extends TestCase {
	
	protected double latitude = 144.96323;
	protected double longitude = -37.81534;
	protected double[] location = {latitude, longitude};
	protected String mapcode = "AAA 6WZ4V.PHC7";
	protected Location gpsLocation;
	protected Location mapcodeLocation;
	
	protected void setUp() throws Exception {
		gpsLocation = new GPSLocation(this.latitude, this.longitude);
		mapcodeLocation = new MapcodeLocation(this.mapcode);
	}

	
	public void testGetLatitudeBySettingMapcodeInGPSClass(){
		this.gpsLocation.setMapcode(this.mapcode);
		assertEquals(Math.round(this.latitude*1000)/1000.0,Math.round(this.gpsLocation.getLatitude()*1000)/1000.0);
	}
	
	public void testGetLongitudefromMapcode() {
		mapcodeLocation = new MapcodeLocation(this.mapcode);
		assertEquals(Math.round(this.longitude*1000)/1000.0,Math.round(this.mapcodeLocation.getLongitude()*1000)/1000.0);
	}
	
	public void testSetMapcode() {
		this.mapcodeLocation.setMapcode("34V.45");
		assertEquals("34V.45",this.mapcodeLocation.getMapcode());
	}

	public void testGetMapcode() {
		mapcodeLocation = new MapcodeLocation(this.mapcode);
		assertEquals(mapcode,this.mapcodeLocation.getMapcode());
	}
	
	public void testGetMapcodeFromGPScoordinates(){
//		this.gpsLocation.setLocation(longitude, latitude );
		this.gpsLocation.setMapcode(mapcode);
		assertEquals(this.mapcode, this.gpsLocation.getMapcode());
	}
	
	public void testGetLatitudefromMapcode() {
		mapcodeLocation = new MapcodeLocation(this.mapcode);
		assertEquals(Math.round(this.latitude*1000)/1000.0,Math.round(this.mapcodeLocation.getLatitude()*1000)/1000.0);
	}
	

	
//	public void testGetLocationfromMapcode() {
//		mapcodeLocation = new MapcodeLocation(this.mapcode);
//		assertEquals(this.location,mapcodeLocation.getLocation());
//	}

	public void testGetLatitude() {
		assertEquals(this.latitude, gpsLocation.getLatitude());
	}

	public void testGetLongitude() {
		assertEquals(this.longitude, gpsLocation.getLongitude());
	}
	
	public void testAsStringGPS() {
		assertEquals(this.latitude+ " " +this.longitude ,gpsLocation.asString());
	}
	
	public void testMapcodeAsStringGPS() {
		this.gpsLocation.setMapcode(this.mapcode);
		assertEquals(this.latitude+ " " +this.longitude ,gpsLocation.asString());
	}
	
	public void testAsStringMapcode() {
		assertEquals(this.mapcode,mapcodeLocation.asString());
	}



}
