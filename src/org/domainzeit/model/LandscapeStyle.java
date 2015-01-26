package org.domainzeit.model;

/**
 * LandscapeStyle contains the style of the landscape.
 * 
 * @invariant You must not mix type: - forest and dessert - mountain and ocean
 *
 * @author Steffen Loskarn
 * @version 1.0
 * @date 19.11.2014
 *
 */

public class LandscapeStyle {

	private boolean mountain;
	private boolean forest;
	private boolean dessert;
	private boolean countryside;
	private boolean beach;
	private boolean steppe;
	private boolean ocean;

	/**
	 * 
	 * @methodtype constructor
	 */
	public LandscapeStyle() {
	}

	/**
	 * @param boolean mountain, boolean forest, boolean dessert, 
	 * boolean countryside, boolean beach, boolean steppe, boolean ocean
	 * @methodtype constructor
	 */
	public LandscapeStyle(boolean mountain, boolean forest, boolean dessert,
			boolean countryside, boolean beach, boolean steppe, boolean ocean) {
		setMountain(mountain);
		setForest(forest);
		setDessert(dessert);
		setSteppe(steppe);
		setBeach(beach);
		setCountryside(countryside);
		setOcean(ocean);
	}

	/**
	 * @param boolean mountain
	 * @methodtype set method
	 */
	public void setMountain(boolean mountain) throws IllegalArgumentException {

		if (mountain && this.ocean)
			throw new IllegalArgumentException("The invariant: You must not mix type: mountain and ocean, was violated");

		this.mountain = mountain;

		assert mountain == this.mountain; // postcondition
	}

	/**
	 * @param boolean forest
	 * @methodtype set method
	 */
	public void setForest(boolean forest) throws RuntimeException {

		if (this.dessert && forest)
			throw new RuntimeException("The invariant: You must not mix type: forest and dessert,  was violated");

		this.forest = forest;

		assert forest == this.forest; // postcondition
	}

	/**
	 * @param boolean dessert
	 * @methodtype set method
	 */
	public void setDessert(boolean dessert) throws RuntimeException {

		if (dessert && this.forest)
			throw new RuntimeException("The invariant: You must not mix type: forest and dessert, was violated");

		this.dessert = dessert;

		assert dessert == this.dessert; // postcondition
	}

	/**
	 * @param boolean steppe
	 * @methodtype set method
	 */
	public void setSteppe(boolean steppe) {
		this.steppe = steppe;

		assert steppe == this.steppe; // postcondition
	}

	/**
	 * @param boolean beach
	 * @methodtype set method
	 */
	public void setBeach(boolean beach) {
		this.beach = beach;

		assert beach == this.beach; // postcondition
	}

	/**
	 * @param boolean countryside
	 * @methodtype set method
	 */
	public void setCountryside(boolean countryside) {
		this.countryside = countryside;

		assert countryside == this.countryside; // postcondition
	}

	/**
	 * @param boolean ocean
	 * @methodtype set method
	 */
	public void setOcean(boolean ocean) {

		if (this.mountain && ocean)
			throw new RuntimeException("The invariant was violated");

		this.ocean = ocean;

		assert ocean == this.ocean; // postcondition
	}

	/**
	 * 
	 * @return boolean
	 * @methodtype get method
	 */
	public boolean getMountain() {
		return this.mountain;
	}

	/**
	 *
	 * @return boolean
	 * @methodtype get method
	 */
	public boolean gettForest() {
		return this.forest;
	}

	/**
	 *
	 * @return boolean
	 * @methodtype get method
	 */
	public boolean getDessert() {
		return this.dessert;
	}

	/**
	 *
	 * @return boolean
	 * @methodtype get method
	 */
	public boolean getSteppe() {
		return this.steppe;
	}

	/**
	 *
	 * @return boolean
	 * @methodtype get method
	 */
	public boolean getBeach() {
		return this.beach;
	}

	/**
	 *
	 * @return boolean
	 * @methodtype get method
	 */
	public boolean getCountryside() {
		return this.countryside;
	}

	/**
	 *
	 * @return boolean
	 * @methodtype get method
	 */
	public boolean getOcean() {
		return this.ocean;
	}

	/**
	 * Returns a simple readable discription of the type.
	 * 
	 * @return String
	 * @methodtype get
	 */
	public String asString() {
		String type = "";
		if (this.mountain)
			type += "mountains, ";
		if (this.dessert)
			type += "dessert, ";
		if (this.ocean)
			type += "ocean, ";
		if (this.steppe)
			type += "steppe, ";
		if (this.beach)
			type += "beach, ";
		if (this.countryside)
			type += "countryside, ";
		if (this.forest)
			type += "forest, ";

		if (type.length() != 0)
			return type.substring(0, type.length() - 2);
		else
			return "";
	}

}
