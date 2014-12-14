package org.wahlzeit.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.DataObject;

/**
 * Landscape Photo. 
 *
 * @author Steffen Loskarn
 * @version 1.0
 * @date 03.12.2014
 *
 */

public class Landscape extends DataObject{
	
	public static final String TYPE = "type";
	public static final String MOUNTAINS = "mountains";
	public static final String BEACH = "beach";
	public static final String COUNTRYSIDE = "countryside";
	public static final String DESSERT = "dessert";
	public static final String STEPPE = "steppe";
	public static final String OCEAN = "ocean";
	public static final String FOREST = "forest";
	public static final String FILTER = "filter";
	
	protected Integer id;
	protected LandscapeType landscapeType = new LandscapeType();
	protected LandscapePhotoFilterEnum filter = LandscapePhotoFilterEnum.NONE;
	
	public Integer getId() {
		return id;
	}

	public Landscape(Integer id) {
		this.id = id;
		incWriteCount();
	}
	
	public Landscape(ResultSet rset) throws SQLException{
		this.readFrom(rset);
	}

	@Override
	public String getIdAsString() {
		return String.valueOf(this.id);
	}
	
	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		this.id = rset.getInt("id");
		
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
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		rset.updateInt("id", this.id);
		
		rset.updateBoolean("mountains", landscapeType.getMountain());
		rset.updateBoolean("dessert", landscapeType.getDessert());
		rset.updateBoolean("ocean", landscapeType.getOcean());
		rset.updateBoolean("steppe", landscapeType.getSteppe());
		rset.updateBoolean("beach", landscapeType.getBeach());
		rset.updateBoolean("countryside", landscapeType.getCountryside());
		rset.updateBoolean("forest", landscapeType.gettForest());
		rset.updateString("filter", filter.name());
	}
	@Override
	public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		stmt.setInt(pos, this.id);
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
	 * @methodtype set method
	 */
	public void setLandscapeType(LandscapeType landscapeType){
		//precondition
		if(landscapeType == null)
		{
			throw new IllegalArgumentException();
		}
		
		this.landscapeType = landscapeType;
		incWriteCount();
		
		//postcondition
		assert landscapeType == this.landscapeType;			//postcondition
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
		//precondition
		if(filter == null)
		{
			throw new IllegalArgumentException();
		}
		
		this.filter = filter;
		incWriteCount();
		
		//postcondition
		assert filter == this.filter;			
	}
	
}
