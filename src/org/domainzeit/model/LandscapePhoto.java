package org.domainzeit.model;

/**
 * Landscape/ Landscape Photo Collaboration: LandscapePhoto has the Client role, which gets 
 * the main domain functionality from Landscape.
 *
 * @author Steffen Loskarn
 * @version 2.0
 * @date 06.12.2014
 *
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

public class LandscapePhoto extends Photo {

	public static final String ID = "landscape_id";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String MOUNTAINS = "mountains";
	public static final String BEACH = "beach";
	public static final String COUNTRYSIDE = "countryside";
	public static final String DESSERT = "dessert";
	public static final String STEPPE = "steppe";
	public static final String OCEAN = "ocean";
	public static final String FOREST = "forest";
	public static final String FILTER = "filter";

	/* Landscape/ Landscape Photo Collaboration: Domain Object */
	protected Landscape landscape;

	/**
	 * 
	 * @methodtype constructor
	 */
	public LandscapePhoto() {
		super();
		initialize();
	}

	/**
	 * 
	 * @methodtype constructor
	 */
	public LandscapePhoto(PhotoId id) {
		super(id);
		initialize();
	}

	/**
	 * @param ResultSet
	 * @methodtype constructor
	 */
	public LandscapePhoto(ResultSet rset) throws SQLException {
		super(rset);
		try{
			assert !rset.isClosed();
		} catch(Exception e) {
			throw new RuntimeException("ResultSet is closed!");
		}
		
	}

	/**
	 * 
	 * @methodtype initialization
	 */
	protected void initialize() {
		this.landscape = LandscapeManager.getInstance().getLandscapeFromId(-1);
	}
	
	/************************* Collaboration Serliazer ********************************/

	/**
	 * @param ResultSet
	 * @methodtype initialization
	 */
	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		super.readFrom(rset);

		this.landscape = LandscapeManager.getInstance().getLandscapeFromId(
				rset.getInt("landscape_id"));
	}

	/**
	 * @param ResultSet
	 * @methodtype command method
	 */
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		super.writeOn(rset);

		rset.updateInt("landscape_id", this.landscape.getId());
	}
	/********************************************************************************************************************/
	/************************* Landscape/ Landscape Photo Collaboration: “Client” role methods ********************************/

	/**
	 *
	 * @return Landscape
	 * @methodtype get method
	 */
	public Landscape getLandscape() {
		return this.landscape;
	}

	/**
	 * @param Landscape
	 * @methodtype set method
	 */
	public void setLandscape(Landscape landscape) {
		// precondition
		if (landscape == null) {
			throw new IllegalArgumentException("Landscape must not be null");
		}

		this.landscape = landscape;
		incWriteCount();

		// postcondition
		assert (this.landscape.equals(landscape));
	}

	/********************************************************************************************************************/

}
