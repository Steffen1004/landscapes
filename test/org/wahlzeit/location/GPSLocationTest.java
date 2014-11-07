package org.wahlzeit.location;

import junit.framework.TestCase;
import static org.junit.Assert.*;

public class GPSLocationTest extends AbstractLocationTest {
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testGetLocation() {
		assertEquals(location, gpsLocation.getLocation());	
	}

}
