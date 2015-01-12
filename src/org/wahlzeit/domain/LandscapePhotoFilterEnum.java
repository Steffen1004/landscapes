package org.wahlzeit.domain;

/**
 * Landscape LandscapeFilterTechniqueEnum.
 *
 * @author Steffen Loskarn
 * @version 1.0
 * @date 28.11.2014
 *
 */

public enum LandscapePhotoFilterEnum implements EnumValue {

	UV_FILTER, SKYLIGHT_FILTER, CLEAR_FILTER, NEUTRAL_DENSITY_FILTER, WARMING_FILTER, COOLING_FILTER, NONE;

	@Override
	/**
	 * Returns a simple readable discription of the LandscapePhotoFilterEnum.
	 * 
	 * @return String
	 * @methodtype get
	 */
	public String asString() {
		return this.name();
	}

	@Override
	/**
	 *
	 * @return EnumValue[]
	 * @methodtype get method
	 */
	public EnumValue[] getAllValues() {
		EnumValue[] allValues = { UV_FILTER, SKYLIGHT_FILTER, CLEAR_FILTER,
				NEUTRAL_DENSITY_FILTER, WARMING_FILTER, COOLING_FILTER, NONE };
		return allValues;
	}

	@Override
	/**
	 *
	 * @return String
	 * @methodtype get method
	 */
	public String getTypeName() {
		return this.getTypeName();
	}

}
