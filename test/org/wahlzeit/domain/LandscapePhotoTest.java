package org.wahlzeit.domain;

import org.wahlzeit.domain.LandscapePhoto;
import org.wahlzeit.domain.LandscapeType;

import junit.framework.TestCase;

public class LandscapePhotoTest extends TestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
	}

		
	public void testNewLandscapePhoto() {
					LandscapePhoto photo = (LandscapePhoto) LandscapePhotoFactory.getInstance().createPhoto();
			 	}
			 	
	public void testNullAssignmentLandscapePhoto(){
		LandscapePhoto photo = (LandscapePhoto) LandscapePhotoFactory.getInstance().createPhoto();
			 try {
					photo.getLandscape().setLandscapePhotoFilterEnum(null);
			 	} catch (IllegalArgumentException as) {
			 		return;
			 	}
		
	}

}
