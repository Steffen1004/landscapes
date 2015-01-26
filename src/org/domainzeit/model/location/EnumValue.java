package org.domainzeit.model.location;

/**
 * Interface EnumValue.
 *
 * @author Steffen Loskarn
 * @version 1.0
 * @date 28.11.2014
 *
 */

public interface EnumValue {

	public String asString();

	public EnumValue[] getAllValues();

	public String getTypeName();

}
