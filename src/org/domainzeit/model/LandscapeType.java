package org.domainzeit.model;

/**
 * 
 * Type Object Collaboration: LandscapeType is the "Type Object" for landscape
 * with the purpose to provide descriptive information common to all instances
 * of a type.
 * 
 * ValueObject Collaboration: LandscapeType has the "Client" role, which gets 
 * the photo filter functionality from the ValueObject LandscapePhotoFilterEnum.
 * 
 * @author Steffen Loskarn
 * @version 2.0
 * @date 12.12.2014
 *
 */

public class LandscapeType {

	protected LandscapeStyle landscapeStyle = new LandscapeStyle();
	/** ValueObject Collaboration: Client role */
	protected LandscapePhotoFilterEnum filter = LandscapePhotoFilterEnum.NONE;

	/**
	 * 
	 * @methodtype constructor
	 */
	public LandscapeType() {
	}

	/**************** Type Object Collaboration: �Type Object� methods for getting and setting the values ******************/
	/**
	 * 
	 * @return LandscapeStyle
	 * @methodtype get method
	 */
	public LandscapeStyle getLandscapeStyle() {
		return this.landscapeStyle;
	}

	/**
	 *
	 * @methodtype set method
	 */
	public void setLandscapeStyle(LandscapeStyle landscapeStyle) {
		// precondition
		if (landscapeStyle == null) {
			throw new IllegalArgumentException("LandscapeStyle is not valid");
		}

		this.landscapeStyle = landscapeStyle;

		// postcondition
		assert landscapeStyle == this.landscapeStyle;
	}

	/************************************** ValueObject Collaboration: Client role ******************************/
	/**
	 *
	 * @return LandscapePhotoFilterEnum
	 * @methodtype get method
	 */
	public LandscapePhotoFilterEnum getLandscapePhotoFilterEnum() {
		return this.filter;
	}

	/**
	 *
	 * @methodtype set method
	 */
	public void setLandscapePhotoFilterEnum(LandscapePhotoFilterEnum filter) {
		// precondition
		if (filter == null) {
			throw new IllegalArgumentException("Landscape Photo Filter is not valid");
		}

		this.filter = filter;

		// postcondition
		assert filter == this.filter;
	}
	/***********************************************************************************************************/
}
