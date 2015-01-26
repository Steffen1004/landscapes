package org.domainzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.SysLog;

/**
 * Landscape factory.
 * Factory-Collaboration: Concrete Factory: implements operation to create LandscapePhotos
 *
 * @author Steffen Loskarn
 * @version 2.0
 * @date 06.12.2014
 *
 */

public class LandscapeFactory {
	
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static LandscapeFactory instance = null;

	/**
	 * Public singleton access method.
	 */
	public static synchronized LandscapeFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic LandscapeFactory");
			setInstance(new LandscapeFactory());
		}

		return instance;
	}

	/**
	 * Method to set the singleton instance of LandscapeFactory.
	 */
	protected static synchronized void setInstance(
			LandscapeFactory landscapeFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize LandscapeFactory twice");
		}

		instance = landscapeFactory;
	}

	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}

	/**
	 * 
	 */
	private LandscapeFactory() {
		super();
	}

	/*******************************Factory-Collaboration: Concrete Factory************************************/
	/**
	 *
	 * @return Landscape
	 * @methodtype factory
	 */
	public Landscape createLandscape(Integer id) {
		return new Landscape(id);
	}

	/**
	 * 
	 * @return Landscape
	 * @methodtype factory
	 */
	public Landscape createLandscape(ResultSet rset) throws SQLException {
		if (rset == null)
			throw new IllegalArgumentException("ResultSet is invalid");
		
		return new Landscape(rset);
	}
	/************************************************************************/

}
