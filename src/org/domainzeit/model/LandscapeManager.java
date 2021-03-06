package org.domainzeit.model;

import org.wahlzeit.services.ObjectManager;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.wahlzeit.main.ServiceMain;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.PhotoTagCollector;
import org.wahlzeit.model.PhotoUtil;
import org.wahlzeit.services.Persistent;
import org.wahlzeit.services.SysLog;

/**
 * 
 * 
 * Manager-Collaboration: Landscape Manager has the manager role creates and
 * manages the object (Landscape)
 * 
 * Serializer-Collaboration: Client role
 *
 * @author Steffen Loskarn
 * @version 1.0
 * @date 06.12.2014
 *
 */

public class LandscapeManager extends ObjectManager {

	/**
	 * 
	 */
	protected static final LandscapeManager instance = new LandscapeManager();

	/**
	 * Manager-Collaboration: Client role (0...n Landscapes)
	 * 
	 * In-memory cache for landscapes
	 */
	protected Map<Integer, Landscape> landscapeCache = new HashMap<Integer, Landscape>();

	protected int currentId = 0;

	/**
	 * 
	 */
	public static final LandscapeManager getInstance() {
		return instance;
	}

	/**
	 * @methodtype constructor
	 */
	protected LandscapeManager() {
	}


	/************************* Manager Collaboration: �Manager� methods ***************************************/
	
	public void setCurrentId(int currentId) {
		if (currentId < 0) {
			throw new IllegalArgumentException(currentId+"<0");
		}

		this.currentId = currentId;
	}

	public int getCurrentId() {
		return currentId;
	}

	public final boolean hasLandscape(Integer id) {
		return getLandscapeFromId(id) != null;
	}

	protected boolean doHasLandscape(Integer id) {
		return this.landscapeCache.containsKey(id);
	}

	protected Landscape doGetLandscapeFromId(Integer id) {
		return this.landscapeCache.get(id);
	}

	private void assertIsNewLandscape(Integer id) {
		if (hasLandscape(id)) {
			throw new IllegalStateException("Landscape already exists!");
		}
	}

	protected void doAddLandscape(Landscape landscape) {
		this.landscapeCache.put(landscape.getId(), landscape);
	}


	public void addLandscape(Landscape landscape) {
		assertIsNewLandscape(landscape.getId());
		doAddLandscape(landscape);

		try {
			PreparedStatement stmt = getReadingStatement("INSERT INTO landscapes(id) VALUES(?)");
			createObject(landscape, stmt, landscape.getId());
			saveLandscape(landscape);
			ServiceMain.getInstance().saveGlobals();
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}



	public Landscape createLandscape() throws Exception {
		this.currentId++;
		Integer id = Integer.valueOf(this.currentId);
		Landscape result = LandscapeFactory.getInstance().createLandscape(
				id);
		addLandscape(result);
		return result;
	}
	
	public void saveLandscape(Landscape landscape) {
		try {
			PreparedStatement stmt = getUpdatingStatement("SELECT * FROM landscapes WHERE id = ?");
			updateObject(landscape, stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}

	public void saveLandscapes() {
		try {
			PreparedStatement stmt = getUpdatingStatement("SELECT * FROM landscapes WHERE id = ?");
			updateObjects(this.landscapeCache.values(), stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}

	public final Landscape getLandscapeFromId(Integer id) {
		if (id == null && id < 0) {
			throw new IllegalArgumentException();
		}
		Landscape result = this.doGetLandscapeFromId(id);
		if (result == null) {
			try {
				PreparedStatement stmt = getReadingStatement("SELECT * FROM landscapes WHERE id = ?");
				result = (Landscape) readObject(stmt, id.intValue());
			} catch (SQLException sex) {
				SysLog.logThrowable(sex);
			}
			if (result != null) {
				doAddLandscape(result);
			}
		}

		return result;
	}

	public Collection<Landscape> loadLandscapes() {
		try {
			ArrayList<Landscape> list = new ArrayList<Landscape>();
			PreparedStatement stmt = getReadingStatement("SELECT * FROM landscapes");
			readObjects(list, stmt);
			for (Iterator<Landscape> i = list.iterator(); i.hasNext();) {
				Landscape landscape = i.next();
				if (!doHasLandscape(landscape.getId())) {
					doAddLandscape(landscape);
				} else {
					SysLog.logSysInfo("landscape",
							landscape.getId().toString(),
							"landscape had already been loaded");
				}
			}
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}

		SysLog.logSysInfo("loaded all landscapes");
		return landscapeCache.values();

	}

	/*******************************************************************************************************/

	/************************* Serializer-Collaboration: "Client" role methods ***************************************/
	
	@Override
	protected Persistent createObject(ResultSet rset) throws SQLException {
		if (rset == null)
			throw new IllegalArgumentException("ResultSet is invalid");
		
		return LandscapeFactory.getInstance().createLandscape(rset);
	}
	/***************************************************************************************************/
}
