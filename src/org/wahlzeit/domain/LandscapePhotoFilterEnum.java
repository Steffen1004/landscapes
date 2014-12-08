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
	
	UV_FILTER,
	SKYLIGHT_FILTER,
	CLEAR_FILTER,
	NEUTRAL_DENSITY_FILTER,
	WARMING_FILTER,
	COOLING_FILTER,
	NONE
	;

	@Override
	public String asString() {

		return this.name();
	}
	
	@Override
	public EnumValue[] getAllValues() {
		EnumValue[] allValues={UV_FILTER,SKYLIGHT_FILTER,
				CLEAR_FILTER,
				NEUTRAL_DENSITY_FILTER,
				WARMING_FILTER,
				COOLING_FILTER,
				NONE};
		return allValues;
	}
	
	@Override
	public String getTypeName() {
		return this.getTypeName();
	}
	
	}
