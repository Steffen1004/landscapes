package org.wahlzeit.domain;

import org.wahlzeit.domain.LandscapeType;

import junit.framework.TestCase;

public class LandscapeTypeTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}


	public void testSetMountainAndSetOcean() {
		LandscapeType landscapetype = new LandscapeType();
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
		LandscapeType landscapetype = new LandscapeType();
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
		LandscapeType landscapetype = new LandscapeType();
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
		LandscapeType landscapetype = new LandscapeType();
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
		
		LandscapeType landscapetype = new LandscapeType(mountain,forest ,dessert, countryside, beach, steppe, ocean);
		
		assertEquals("mountains, dessert, steppe, beach, countryside", landscapetype.asString());
	}

}
