package org.wahlzeit.domain;

import org.wahlzeit.domain.LandscapePhoto;
import org.wahlzeit.domain.LandscapeType;

import junit.framework.TestCase;

public class LandscapePhotoTest extends TestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
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
	
	public void testGetLandscapePhotoFilter(){
		LandscapePhoto landscapePhoto = new LandscapePhoto();
		LandscapePhotoFilterEnum filter = LandscapePhotoFilterEnum.valueOf("UV_FILTER");
		landscapePhoto.setLandscapePhotoFilterEnum(filter);
		
		assertEquals("UV_FILTER", landscapePhoto.getLandscapePhotoFilterEnum());
		
	}

}
