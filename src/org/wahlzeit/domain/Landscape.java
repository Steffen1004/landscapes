package org.wahlzeit.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.DataObject;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;

/**
 * Type Object Collaboration: The “Base Object” Landscape provides instance
 * specific functionality, i.e. ID and name, and delegates type-specific
 * requests to type object (LandscapeType).
 * 
 * Landscape/ LandscapePhoto Collaboration: Landscape is the "Domain Object"
 * with the purpose to provide main domain functionality.
 * 
 * Manager Collaboration: Domain Object
 * 
 * Factory-Collaboration: Concrete Product: defines a product object to be created by the corresponding concrete factory.
 *
 * @author Steffen Loskarn
 * @version 2.0
 * @date 14.12.2014
 *
 */

public class Landscape extends DataObject {
	
	/* Manager Collaboration: Domain Object */
	private Integer id;
	/****************************************/
	private String name;

	/* Type Object Collaboration: Type Object */
	private LandscapeType type = new LandscapeType();
	/****************************************/

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
	public Landscape(Integer id, String name) {
		this.id = id;
		this.name = name;
		incWriteCount();
	}

	/**
	 * 
	 * @methodtype constructor
	 */
	public Landscape(ResultSet rset) throws SQLException {
		this.readFrom(rset);
	}

	/********************** Landscape/ LandscapePhoto Collaboration: "Domain Object" methods ****************************/
	/**
	 *
	 * @return Integer
	 * @methodtype get method
	 */
	public Integer getId() {
		return id;
	}

	/**
	 *
	 * @return String
	 * @methodtype get method
	 */
	public String getName() {
		return this.name;
	}

	/**
	 *
	 * @methodtype set method
	 */
	public void setName(String name) {
		// precondition
		if (name == "") {
			throw new IllegalArgumentException();
		}

		this.name = name;

		// postcondition
		assert this.name == name;
	}

	@Override
	/**
	 * Returns a simple readable discription of the id.
	 * @return String
	 * @methodtype get
	 */
	public String getIdAsString() {
		return String.valueOf(this.id);
	}

	/**
	 * Returns a simple readable discription of the landscape.
	 * 
	 * @return String
	 * @methodtype get
	 */
	public String asString() {
		return "ID: " + this.id + ", Name: " + this.getName();
	}

	/*************************Type Object Collaboration: “Base Object” methods +
	 *  Landscape/ LandscapePhoto Collaboration: "Domain Object" methods *********************/
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
		// precondition
		if (type == null) {
			throw new IllegalArgumentException();
		}
		this.type = type;
		incWriteCount();

		// postcondition
		assert this.type == type;
	}

	/********************************************************************************************************************/

	/***************** Collaboration: Serializer*****************************************************************/
	@Override
	/**
	 * 
	 *@methodtype initialization 
	 */
	public void readFrom(ResultSet rset) throws SQLException {
		this.id = rset.getInt("id");
		this.name = rset.getString("name");
		boolean mountain = rset.getBoolean("mountains");
		boolean dessert = rset.getBoolean("dessert");
		boolean ocean = rset.getBoolean("ocean");
		boolean steppe = rset.getBoolean("steppe");
		boolean beach = rset.getBoolean("beach");
		boolean countryside = rset.getBoolean("countryside");
		boolean forest = rset.getBoolean("forest");

		this.type.setLandscapeStyle(new LandscapeStyle(mountain, forest,
				dessert, countryside, beach, steppe, ocean));
		this.type.setLandscapePhotoFilterEnum(LandscapePhotoFilterEnum
				.valueOf(rset.getString("filter")));
	}

	@Override
	/**
	 * 
	 *@methodtype command method  
	 */
	public void writeOn(ResultSet rset) throws SQLException {
		rset.updateInt("id", this.id);
		rset.updateString("name", this.name);
		rset.updateBoolean("mountains", this.type.getLandscapeStyle()
				.getMountain());
		rset.updateBoolean("dessert", this.type.getLandscapeStyle()
				.getDessert());
		rset.updateBoolean("ocean", this.type.getLandscapeStyle().getOcean());
		rset.updateBoolean("steppe", this.type.getLandscapeStyle().getSteppe());
		rset.updateBoolean("beach", this.type.getLandscapeStyle().getBeach());
		rset.updateBoolean("countryside", this.type.getLandscapeStyle()
				.getCountryside());
		rset.updateBoolean("forest", this.type.getLandscapeStyle().gettForest());
		rset.updateString("filter", this.type.getLandscapePhotoFilterEnum()
				.asString());
	}

	@Override
	/**
	 * 
	 *@methodtype command method  
	 */
	public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		stmt.setInt(pos, this.id);
	}
	/********************************************************************************************************************/
}
