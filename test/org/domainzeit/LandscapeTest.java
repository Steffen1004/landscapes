package org.domainzeit;

import org.domainzeit.model.Landscape;
import org.domainzeit.model.LandscapeManager;

import junit.framework.TestCase;

public class LandscapeTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testNewLandscape() throws Exception 
	{
		Landscape landscape = LandscapeManager.getInstance().createLandscape();
	}
}
