package org.domainzeit.location;



import org.domainzeit.exception.LocationException;
import org.domainzeit.model.location.GPSLocation;
import org.domainzeit.model.location.Location;
import org.domainzeit.model.location.MapcodeLocation;

import com.mapcode.MapcodeCodec;

import junit.framework.TestCase;
import static org.junit.Assert.*;

public class LocationTest extends TestCase {

	protected double latitude = 144.96323;
	protected double longitude = -37.81534;
	protected double[] location = { latitude, longitude };
	protected String mapcode = "AAA 6WZ4V.PHC7";
	protected Location gpsLocation;
	protected Location mapcodeLocation;

	protected void setUp() throws Exception {
		gpsLocation = new GPSLocation(this.latitude, this.longitude);
		mapcodeLocation = new MapcodeLocation(this.mapcode);
	}

	public void testGetLatitudeBySettingMapcodeInGPSClass() {
		try {
			this.gpsLocation.setMapcode(this.mapcode);
		} catch (LocationException e) {
			e.printStackTrace();
		}
		assertEquals(Math.round(this.latitude * 1000) / 1000.0,
				Math.round(this.gpsLocation.getLatitude() * 1000) / 1000.0);
	}

	public void testGetLongitudefromMapcode() {
		try {
			mapcodeLocation = new MapcodeLocation(this.mapcode);
		} catch (LocationException e) {
			e.printStackTrace();
		}
		assertEquals(Math.round(this.longitude * 1000) / 1000.0,
				Math.round(this.mapcodeLocation.getLongitude() * 1000) / 1000.0);
	}

	public void testSetMapcode() {
		try {
			this.mapcodeLocation.setMapcode("34V.45");
		} catch (LocationException e) {
			e.printStackTrace();
		}
		assertEquals("34V.45", this.mapcodeLocation.getMapcode());
	}

	public void testGetMapcode() {
		try {
			mapcodeLocation = new MapcodeLocation(this.mapcode);
		} catch (LocationException e) {
			e.printStackTrace();
		}
		assertEquals(mapcode, this.mapcodeLocation.getMapcode());
	}

	public void testGetMapcodeFromGPScoordinates() {
		try {
			this.gpsLocation.setMapcode(mapcode);
		} catch (LocationException e) {
			e.printStackTrace();
		}
		assertEquals(this.mapcode, this.gpsLocation.getMapcode());
	}

	public void testGetLatitudefromMapcode() {
		try {
			mapcodeLocation = new MapcodeLocation(this.mapcode);
		} catch (LocationException e) {
			e.printStackTrace();
		}
		assertEquals(Math.round(this.latitude * 1000) / 1000.0,
				Math.round(this.mapcodeLocation.getLatitude() * 1000) / 1000.0);
	}

	public void testGetLatitude() {
		assertEquals(this.latitude, gpsLocation.getLatitude());
	}

	public void testGetLongitude() {
		assertEquals(this.longitude, gpsLocation.getLongitude());
	}

	public void testAsStringGPS() {
		assertEquals(this.latitude + " " + this.longitude,
				gpsLocation.asString());
	}

	public void testMapcodeAsStringGPS() {
		try {
			this.gpsLocation.setMapcode(this.mapcode);
		} catch (LocationException e) {
			e.printStackTrace();
		}
		assertEquals(this.latitude + " " + this.longitude,
				gpsLocation.asString());
	}

	public void testAsStringMapcode() {
		assertEquals(this.mapcode, mapcodeLocation.asString());
	}

}
