package org.wahlzeit.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.SysLog;

/**
 * Landscape photo factory. 
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
	protected static synchronized void setInstance(LandscapePhotoFactory landscapePhotoFactory) {
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
	 * 
	 */
	private LandscapePhotoFactory() {
		super();
	}
	

	/**
	 * 
	 * @methodtype factory
	 */
	public Landscape createLandscape(Integer id) {
		return new Landscape(id);
	}
	

	/**
	 * 
	 * @methodtype factory
	 */
	public Landscape createLandscape(ResultSet rset) throws SQLException {
		return new Landscape(rset);
	}

	/**
	 * 
	 * @methodtype factory
	 */
	public Photo createPhoto() {
		return new LandscapePhoto();
	}
	
	/**
	 * 
	 *  @methodtype factory
	 */
	public Photo createPhoto(PhotoId id) {
		return new LandscapePhoto(id);
	}
	
	/**
	 * 
	 *  @methodtype factory
	 */
	public Photo createPhoto(ResultSet rs) throws SQLException {
		return new LandscapePhoto(rs);
	}
}
