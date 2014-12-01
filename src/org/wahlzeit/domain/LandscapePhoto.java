package org.wahlzeit.domain;

/**
 * Landscape Photo. 
 *
 * @author Steffen Loskarn
 * @version 1.0, 19.11.2014
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
	
	protected LandscapeType landscapeType = new LandscapeType();
	protected LandscapePhotoFilterEnum filter = LandscapePhotoFilterEnum.NONE;

	/**
	 * 
	 * @methodtype constructor
	 */
	public LandscapePhoto(){
		super();
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public LandscapePhoto(PhotoId id){
		super(id);
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public LandscapePhoto(ResultSet rset) throws SQLException {
		readFrom(rset);
	}
	
	/**
	 * 
	 *@methodtype initialization 
	 */
	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		super.readFrom(rset);
		
		boolean mountain = rset.getBoolean("mountains");
		boolean dessert = rset.getBoolean("dessert");
		boolean ocean = rset.getBoolean("ocean");
		boolean steppe = rset.getBoolean("steppe");
		boolean beach = rset.getBoolean("beach");
		boolean countryside = rset.getBoolean("countryside");
		boolean forest = rset.getBoolean("forest");
		
		this.landscapeType = new LandscapeType(mountain,forest ,dessert, countryside, beach, steppe, ocean);
		this.filter = LandscapePhotoFilterEnum.valueOf(rset.getString("filter"));
	}
	
	/**
	 * 
	 *@methodtype command method  
	 */
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		super.writeOn(rset);
		
		rset.updateBoolean("mountains", landscapeType.getMountain());
		rset.updateBoolean("dessert", landscapeType.getDessert());
		rset.updateBoolean("ocean", landscapeType.getOcean());
		rset.updateBoolean("steppe", landscapeType.getSteppe());
		rset.updateBoolean("beach", landscapeType.getBeach());
		rset.updateBoolean("countryside", landscapeType.getCountryside());
		rset.updateBoolean("forest", landscapeType.gettForest());
		rset.updateString("filter", filter.name());
	}
	
	/**
	 *
	 * @methodtype set method
	 */
	public void setLandscapeType(LandscapeType landscapeType){
		this.landscapeType = landscapeType;
		incWriteCount();
		
		assert landscapeType == this.landscapeType;			//postcondition
	}

	/**
	 *
	 * @methodtype get method
	 */
	public String getLandscapeType(){
		return this.landscapeType.asString();
	}
	
	/**
	 *
	 * @methodtype get method
	 */
	public LandscapePhotoFilterEnum getLandscapePhotoFilterEnum(){
		return this.filter;
	}
	
	/**
	 *
	 * @methodtype set method
	 */
	public void setLandscapePhotoFilterEnum(LandscapePhotoFilterEnum filter){
		this.filter = filter;
		incWriteCount();
		
		assert filter == this.filter;			//postcondition
	}
	
	
}
