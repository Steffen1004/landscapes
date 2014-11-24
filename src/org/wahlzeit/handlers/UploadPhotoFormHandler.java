/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.handlers;

import java.util.*;
import java.io.*;

import org.wahlzeit.location.GPSLocation;
import org.wahlzeit.location.LandscapePhoto;
import org.wahlzeit.location.LandscapeType;
import org.wahlzeit.location.Location;
import org.wahlzeit.model.*;
import org.wahlzeit.services.*;
import org.wahlzeit.utils.*;
import org.wahlzeit.webparts.*;

/**
 * 
 * @author dirkriehle
 *
 */
public class UploadPhotoFormHandler extends AbstractWebFormHandler {
	
	/**
	 *
	 */
	public UploadPhotoFormHandler() {
		initialize(PartUtil.UPLOAD_PHOTO_FORM_FILE, AccessRights.USER);
	}
	
	/**
	 * 
	 */
	protected void doMakeWebPart(UserSession us, WebPart part) {
		Map<String, Object> args = us.getSavedArgs();
		part.addStringFromArgs(args, UserSession.MESSAGE);

		part.maskAndAddStringFromArgs(args, Photo.TAGS);
	}
	
	/**
	 * 
	 */
	protected String doHandlePost(UserSession us, Map args) {
		String tags = us.getAndSaveAsString(args, Photo.TAGS);
		
		
		double lat=0.0;
		double lon=0.0;
		
		try {
			lat = Double.parseDouble(us.getAndSaveAsString(args, Photo.LAT));
			lon = Double.parseDouble(us.getAndSaveAsString(args, Photo.LON));
		}
		catch(Exception e)
		{
			lat =0.0;
			lon = 0.0;
		}
		
		String mapcode = us.getAndSaveAsString(args, Photo.MAPCODE);
		Location location;
		location = new GPSLocation(lat, lon);
		
		if(lat == 0.0 && lon == 0.0 && mapcode != "") {
			location.setMapcode(mapcode);
		}

		if (!StringUtil.isLegalTagsString(tags)) {
			us.setMessage(us.cfg().getInputIsInvalid());
			return PartUtil.UPLOAD_PHOTO_PAGE_NAME;
		}

		try {
			PhotoManager pm = PhotoManager.getInstance();
			String sourceFileName = us.getAsString(args, "fileName");
			File file = new File(sourceFileName);
			Photo photo = pm.createPhoto(file);

			String targetFileName = SysConfig.getBackupDir().asString() + photo.getId().asString();
			createBackup(sourceFileName, targetFileName);
		
			User user = (User) us.getClient();
			user.addPhoto(photo); 
			
			photo.setTags(new Tags(tags));
			
			photo.setLocation(location);
			
			doHandleLandscapePhotoPost(photo, us, args);
			
			pm.savePhoto(photo);

			StringBuffer sb = UserLog.createActionEntry("UploadPhoto");
			UserLog.addCreatedObject(sb, "Photo", photo.getId().asString());
			UserLog.log(sb);
			
			us.setTwoLineMessage(us.cfg().getPhotoUploadSucceeded(), us.cfg().getKeepGoing());
		} catch (Exception ex) {
			SysLog.logThrowable(ex);
			us.setMessage(us.cfg().getPhotoUploadFailed());
		}
		
		return PartUtil.UPLOAD_PHOTO_PAGE_NAME;
	}
	
	public void doHandleLandscapePhotoPost(Photo photo, UserSession us, Map args){
		if(photo instanceof LandscapePhoto){
		LandscapePhoto landscapePhoto = (LandscapePhoto)photo;
		try{
		boolean mountain = Boolean.parseBoolean(us.getAndSaveAsString(args, LandscapePhoto.MOUNTAINS));
		boolean dessert = Boolean.parseBoolean(us.getAndSaveAsString(args, LandscapePhoto.DESSERT));
		boolean ocean = Boolean.parseBoolean(us.getAndSaveAsString(args, LandscapePhoto.OCEAN));
		boolean steppe = Boolean.parseBoolean(us.getAndSaveAsString(args, LandscapePhoto.STEPPE));
		boolean beach = Boolean.parseBoolean(us.getAndSaveAsString(args, LandscapePhoto.BEACH));
		boolean countryside = Boolean.parseBoolean(us.getAndSaveAsString(args, LandscapePhoto.COUNTRYSIDE));
		boolean forest = Boolean.parseBoolean(us.getAndSaveAsString(args, LandscapePhoto.FOREST));
		
		LandscapeType landscapeType = new LandscapeType(mountain,forest ,dessert, countryside, beach, steppe, ocean);
		landscapePhoto.setLandscapeType(landscapeType);
		}	
		catch(Exception e)
		{}
		}
	}
	
	/**
	 * 
	 */
	protected void createBackup(String sourceName, String targetName) {
		try {
			File sourceFile = new File(sourceName);
			InputStream inputStream = new FileInputStream(sourceFile);
			File targetFile = new File(targetName);
			OutputStream outputStream = new FileOutputStream(targetFile);
			// @FIXME IO.copy(inputStream, outputStream);
		} catch (Exception ex) {
			SysLog.logSysInfo("could not create backup file of photo");
			SysLog.logThrowable(ex);			
		}
	}
}
