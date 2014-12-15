package org.wahlzeit.domain;

/**
 * Landscape Photo. 
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

public class LandscapePhoto extends Photo{

	public static final String TYPE = "type";
	public static final String MOUNTAINS = "mountains";
	public static final String BEACH = "beach";
	public static final String COUNTRYSIDE = "countryside";
	public static final String DESSERT = "dessert";
	public static final String STEPPE = "steppe";
	public static final String OCEAN = "ocean";
	public static final String FOREST = "forest";
	public static final String FILTER = "filter";
	
	protected Landscape landscape;
	

	/**
	 * 
	 * @methodtype constructor
	 */
	public LandscapePhoto(){
		super();
		initialize();
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public LandscapePhoto(PhotoId id){
		super(id);
		initialize();
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public LandscapePhoto(ResultSet rset) throws SQLException {
//		readFrom(rset);
		super(rset);
	}
	
	/**
	 * 
	 *@methodtype initialization 
	 */
	protected void initialize(){
		this.landscape = LandscapeManager.getInstance().getLandscapeFromId(-1);
	}
	
	/**
	 * 
	 *@methodtype initialization 
	 */
	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		super.readFrom(rset);
		
		this.landscape = LandscapeManager.getInstance().getLandscapeFromId(rset.getInt("landscape_id"));
	}
	
	/**
	 *
	 * @methodtype get method
	 */
	public Landscape getLandscape() {
		return this.landscape;
	}
	
	/**
	 *
	 * @methodtype set method
	 */
	public void setLandscape(Landscape landscape)
	{
		//precondition
		if(landscape == null)
		{
			throw new IllegalArgumentException();
		}
		
		this.landscape=landscape;
		incWriteCount();
		
		//postcondition
		assert(this.landscape.equals(landscape));
	}
	
	/**
	 * 
	 *@methodtype command method  
	 */
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		super.writeOn(rset);
		
		rset.updateInt("landscape_id", this.landscape.getId());
	}
	
}
