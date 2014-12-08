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

	protected Landscape landscape;
	
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
	 *@methodtype command method  
	 */
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		super.writeOn(rset);
		
		rset.updateInt("landscape_id", this.landscape.getId());
	}
	
}
