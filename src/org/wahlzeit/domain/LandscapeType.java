package org.wahlzeit.domain;

/**
 * LandscapeType is the "Type Object" for landscape. 
 *
 * @author Steffen Loskarn
 * @version 1.0
 * @date 14.11.2014
 *
 */

public class LandscapeType {

	protected LandscapeStyle landscapeStyle = new LandscapeStyle();
	protected LandscapePhotoFilterEnum filter = LandscapePhotoFilterEnum.NONE;

	/**
	 * 
	 * @methodtype constructor
	 */
	public LandscapeType()
	{}
	
	/**
	 *
	 * @methodtype get method
	 */
	public LandscapeStyle getLandscapeStyle(){
		return this.landscapeStyle;
	}
	
	/**
	 *
	 * @methodtype set method
	 */
	public void setLandscapeStyle(LandscapeStyle landscapeStyle){
		//precondition
		if(landscapeStyle == null)
		{
			throw new IllegalArgumentException();
		}
		
		this.landscapeStyle = landscapeStyle;
		
		//postcondition
		assert landscapeStyle == this.landscapeStyle;			
	}

	
	/**
	 *
	 * @methodtype get method
	 */
	public LandscapePhotoFilterEnum getLandscapePhotoFilterEnum(){
		return this.filter;
	}
	
	/**
	 *
	 * @methodtype set method
	 */
	public void setLandscapePhotoFilterEnum(LandscapePhotoFilterEnum filter){
		//precondition
		if(filter == null)
		{
			throw new IllegalArgumentException();
		}
		
		this.filter = filter;
		
		//postcondition
		assert filter == this.filter;			
	}
}
