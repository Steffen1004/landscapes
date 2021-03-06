package org.domainzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.SysLog;

/**
 * Landscape photo factory.
 * Factory-Collaboration: Concrete Factory: implements operation to create LandscapePhotos
 *
 * @author Steffen Loskarn
 * @version 2.0
 * @date 06.12.2014
 *
 */

public class LandscapePhotoFactory extends PhotoFactory {

	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static LandscapePhotoFactory instance = null;

	/**
	 * Public singleton access method.
	 */
	public static synchronized LandscapePhotoFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic LandscapePhotoFactory");
			setInstance(new LandscapePhotoFactory());
		}

		return instance;
	}

	/**
	 * Method to set the singleton instance of LandscapePhotoFactory.
	 */
	protected static synchronized void setInstance(
			LandscapePhotoFactory landscapePhotoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize LandscapePhotoFactory twice");
		}

		instance = landscapePhotoFactory;
	}

	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}

	/**
	 * @methodtype constructor
	 */
	private LandscapePhotoFactory() {
		super();
	}

	/*******************************Factory-Collaboration: Concrete Factory************************************/
	
	/**
	 * @return Photo
	 * @methodtype factory
	 */
	public Photo createPhoto() {
		return new LandscapePhoto();
	}
	
	/**
	 * @return Photo
	 * @methodtype factory
	 */
	public Photo createPhoto(PhotoId id) {
		return new LandscapePhoto(id);
	}
	
	/**
	 * @return Photo
	 * @methodtype factory 
	 */
	public Photo createPhoto(ResultSet rs) throws SQLException {
		return new LandscapePhoto(rs);
	}
	/************************************************************************/
}
