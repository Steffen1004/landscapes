package org.wahlzeit.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.DataObject;

/**
 * The “Object” Landscape provides instance specific functionality, 
 * i.e. ID and description, and delegates type-specific requests to type object (LandscapeType). 
 *
 * @author Steffen Loskarn
 * @version 2.0
 * @date 14.12.2014
 *
 */

public class Landscape extends DataObject{
		
	private Integer id;
	private String description;
	private LandscapeType type = new LandscapeType();;
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public Landscape(Integer id) {
		this.id = id;
		incWriteCount();
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public Landscape(Integer id, String description) {
		this.id = id;
		this.description = description;
		incWriteCount();
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public Landscape(ResultSet rset) throws SQLException{
		this.readFrom(rset);
	}
	
	/**
	 *
	 * @methodtype get method
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 *
	 * @methodtype get method
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 *
	 * @methodtype get method
	 */
	public LandscapeType getType() {
		return type;
	}	
	
	/**
	 *
	 * @methodtype set method
	 */
	public void setLandscapeType(LandscapeType type) {
		//precondition
		if(type == null)
		{
			throw new IllegalArgumentException();
		}
		this.type = type;
		incWriteCount();
		
		//postcondition
		assert this.type == type;
	}
	
	/**
	 *
	 * @methodtype set method
	 */
	public void setDescription(String description) {
		//precondition
		if(description == "")
		{
			throw new IllegalArgumentException();
		}
		
		this.description = description;
			
		//postcondition
		assert this.description == description;
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
		
		this.type.setLandscapeStyle(new LandscapeStyle(mountain,forest ,dessert, countryside, beach, steppe, ocean));
		this.type.setLandscapePhotoFilterEnum(LandscapePhotoFilterEnum.valueOf(rset.getString("filter")));
	}
	
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		rset.updateInt("id", this.id);
		
		rset.updateBoolean("mountains", this.type.getLandscapeStyle().getMountain());
		rset.updateBoolean("dessert", this.type.getLandscapeStyle().getDessert());
		rset.updateBoolean("ocean", this.type.getLandscapeStyle().getOcean());
		rset.updateBoolean("steppe", this.type.getLandscapeStyle().getSteppe());
		rset.updateBoolean("beach", this.type.getLandscapeStyle().getBeach());
		rset.updateBoolean("countryside", this.type.getLandscapeStyle().getCountryside());
		rset.updateBoolean("forest", this.type.getLandscapeStyle().gettForest());
		rset.updateString("filter", this.type.getLandscapePhotoFilterEnum().name());
	}
	
	@Override
	public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		stmt.setInt(pos, this.id);
	}
}
