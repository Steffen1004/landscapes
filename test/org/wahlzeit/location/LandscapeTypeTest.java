package org.wahlzeit.location;

import junit.framework.TestCase;

public class LandscapeTypeTest extends TestCase {
	
//	private boolean mountain= true;
//	private boolean forest= true;
//	private boolean dessert= true;
//	private boolean countryside= true; 
//	private boolean beach= true;
//	private boolean steppe= true;
//	private boolean ocean= true;
//	
//	LandscapeType landscapetype = new LandscapeType(mountain,forest ,dessert, countryside, beach, steppe, ocean);

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


//	public void testSetForest() {
//		fail("Not yet implemented");
//	}
//
//	public void testSetDessert() {
//		fail("Not yet implemented");
//	}
//
//	public void testSetSteppe() {
//		fail("Not yet implemented");
//	}
//
//	public void testSetBeach() {
//		fail("Not yet implemented");
//	}
//
//	public void testSetCountryside() {
//		fail("Not yet implemented");
//	}
//
//	public void testSetOcean() {
//		fail("Not yet implemented");
//	}
//
//	public void testGetMountain() {
//		fail("Not yet implemented");
//	}
//
//	public void testGettForest() {
//		fail("Not yet implemented");
//	}
//
//	public void testGetDessert() {
//		fail("Not yet implemented");
//	}
//
//	public void testGetSteppe() {
//		fail("Not yet implemented");
//	}
//
//	public void testGetBeach() {
//		fail("Not yet implemented");
//	}
//
//	public void testGetCountryside() {
//		fail("Not yet implemented");
//	}
//
//	public void testGetOcean() {
//		fail("Not yet implemented");
//	}

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
