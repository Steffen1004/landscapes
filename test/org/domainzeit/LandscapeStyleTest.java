package org.domainzeit;

import org.domainzeit.model.LandscapeStyle;

import junit.framework.TestCase;

public class LandscapeStyleTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}


	public void testSetMountainAndSetOcean() {
		LandscapeStyle landscapetype = new LandscapeStyle();
		landscapetype.setMountain(true);

		boolean exceptionThrown = false;
		try
		{
			landscapetype.setOcean(true);
		} catch(Exception e)
		{
			exceptionThrown = true;
		}

		assertTrue(exceptionThrown);
	}
	
	public void testSetOceanAndSetMountain() {
		LandscapeStyle landscapetype = new LandscapeStyle();
		landscapetype.setOcean(true);

		boolean exceptionThrown = false;
		try
		{
			landscapetype.setMountain(true);
		} catch(Exception e)
		{
			exceptionThrown = true;
		}

		assertTrue(exceptionThrown);
	}
	
	public void testSetForestAndSetDessert() {
		LandscapeStyle landscapetype = new LandscapeStyle();
		landscapetype.setForest(true);

		boolean exceptionThrown = false;
		try
		{
			landscapetype.setDessert(true);
		} catch(Exception e)
		{
			exceptionThrown = true;
		}

		assertTrue(exceptionThrown);
	}
	
	public void testSetDessertAndSetForest() {
		LandscapeStyle landscapetype = new LandscapeStyle();
		landscapetype.setDessert(true);

		boolean exceptionThrown = false;
		try
		{
			landscapetype.setForest(true);
		} catch(Exception e)
		{
			exceptionThrown = true;
		}

		assertTrue(exceptionThrown);
	}

	public void testAsString() {
		
		boolean mountain= true;
		boolean forest= false;
		boolean dessert= true;
		boolean countryside= true; 
		boolean beach= true;
		boolean steppe= true;
		boolean ocean= false;
		
		LandscapeStyle landscapetype = new LandscapeStyle(mountain,forest ,dessert, countryside, beach, steppe, ocean);
		
		assertEquals("mountains, dessert, steppe, beach, countryside", landscapetype.asString());
	}

}
