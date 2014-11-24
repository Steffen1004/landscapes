package org.wahlzeit.domain;

import org.wahlzeit.domain.LandscapePhoto;
import org.wahlzeit.domain.LandscapeType;

import junit.framework.TestCase;

public class LandscapePhotoTest extends TestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testReadFrom() {
		fail("Not yet implemented");
	}

	public void testWriteOn() {
		fail("Not yet implemented");
	}

	public void testLandscapePhoto() {
		fail("Not yet implemented");
	}

	public void testLandscapePhotoPhotoId() {
		fail("Not yet implemented");
	}

	public void testLandscapePhotoResultSet() {
		fail("Not yet implemented");
	}


	public void testGetLandscapeType() {
		
		LandscapePhoto landscapePhoto = new LandscapePhoto();
		
		boolean mountain= true;
		boolean forest= false;
		boolean dessert= true;
		boolean countryside= true; 
		boolean beach= true;
		boolean steppe= true;
		boolean ocean= false;
		
		LandscapeType landscapetype = new LandscapeType(mountain,forest ,dessert, countryside, beach, steppe, ocean);
		
		landscapePhoto.setLandscapeType(landscapetype);
		assertEquals("mountains, dessert, steppe, beach, countryside", landscapePhoto.getLandscapeType());
	}

}
