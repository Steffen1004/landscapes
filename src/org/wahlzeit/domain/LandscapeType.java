package org.wahlzeit.domain;

/**
 * LandscapeType contains the type of photo.  
 * @invariant You must not mix type:
 * - forest and dessert
 * - mountain and ocean 
 *
 * @author Steffen Loskarn
 * @version 1.0, 19.11.2014
 *
 */

public class LandscapeType {
	
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
	public LandscapeType(){}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public LandscapeType(boolean mountain, boolean forest, boolean dessert, boolean countryside, boolean beach, boolean steppe, boolean ocean){
		setMountain(mountain);
		setForest(forest);
		setDessert(dessert);
		setSteppe(steppe);
		setBeach(beach);
		setCountryside(countryside);
		setOcean(ocean);
	}
	
	/**
	 *
	 * @methodtype set method
	 */
	public void setMountain(boolean mountain) throws RuntimeException {
		
		if(mountain && this.ocean)
			throw new RuntimeException("The invariant was violated");
		
		this.mountain= mountain;
		
		assert mountain == this.mountain; //postcondition
	}
	
	/**
	 *
	 * @methodtype set method
	 */
	public void setForest(boolean forest) throws RuntimeException {
		
		if(this.dessert && forest)
			throw new RuntimeException("The invariant was violated");
		
		this.forest= forest;
		
		assert forest == this.forest; //postcondition
	}
	
	/**
	 *
	 * @methodtype set method
	 */
	public void setDessert(boolean dessert) throws RuntimeException {
		
		if(dessert && this.forest)
			throw new RuntimeException("The invariant was violated");
		
		this.dessert= dessert;
		
		assert dessert == this.dessert; //postcondition
	}
	
	/**
	 *
	 * @methodtype set method
	 */
	public void setSteppe(boolean steppe){
		this.steppe= steppe;
		
		assert steppe == this.steppe; //postcondition
	}
	
	/**
	 *
	 * @methodtype set method
	 */
	public void setBeach(boolean beach){
		this.beach= beach;
		
		assert beach == this.beach; //postcondition
	}
	
	/**
	 *
	 * @methodtype set method
	 */
	public void setCountryside(boolean countryside){
		this.countryside= countryside;
		
		assert countryside == this.countryside; //postcondition
	}
	
	/**
	 *
	 * @methodtype set method
	 */
	public void setOcean(boolean ocean){
		
		if(this.mountain && ocean)
			throw new RuntimeException("The invariant was violated");
		
		this.ocean= ocean;
		
		assert ocean == this.ocean; //postcondition
	}
	
	/**
	 *
	 * @methodtype get method
	 */
	public boolean getMountain(){
		return this.mountain;
	}
	
	/**
	 *
	 * @methodtype get method
	 */
	public boolean gettForest(){
		return this.forest;
	}
	
	/**
	 *
	 * @methodtype get method
	 */
	public boolean getDessert(){
		return this.dessert;
	}
	
	/**
	 *
	 * @methodtype get method
	 */
	public boolean getSteppe(){
		return this.steppe;
	}
	
	/**
	 *
	 * @methodtype get method
	 */
	public boolean getBeach(){
		return this.beach;
	}
	
	/**
	 *
	 * @methodtype get method
	 */
	public boolean getCountryside(){
		return this.countryside;
	}
	
	/**
	 *
	 * @methodtype get method
	 */
	public boolean getOcean(){
		return this.ocean;
	}
	
	/**
	 * Returns a simple readable discription of the type.
	 * @return String
	 * @methodtype get
	 */
	public String asString(){
		String type="";
		if(this.mountain)
			type+="mountains, ";
		if(this.dessert)
			type+="dessert, ";
		if(this.ocean)
			type+="ocean, ";
		if(this.steppe)
			type +="steppe, ";
		if(this.beach)
			type+="beach, ";
		if(this.countryside)
			type+="countryside, ";
		if(this.forest)
			type+="forest, ";
		
		if(type.length()!=0)
		return type.substring(0,type.length()-2);
		else
		return "";
	}

}
