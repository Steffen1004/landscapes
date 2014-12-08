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

import org.wahlzeit.domain.GPSLocation;
import org.wahlzeit.domain.Landscape;
import org.wahlzeit.domain.LandscapeManager;
import org.wahlzeit.domain.LandscapePhoto;
import org.wahlzeit.domain.LandscapePhotoFilterEnum;
import org.wahlzeit.domain.LandscapeType;
import org.wahlzeit.domain.Location;
import org.wahlzeit.model.*;
import org.wahlzeit.webparts.*;

/**
 * 
 * @author dirkriehle
 *
 */
public class AdminUserPhotoFormHandler extends AbstractWebFormHandler {

	/**
	 *
	 */
	public AdminUserPhotoFormHandler() {
		initialize(PartUtil.ADMIN_USER_PHOTO_FORM_FILE, AccessRights.ADMINISTRATOR);
	}
	
	/**
	 * 
	 */
	protected void doMakeWebPart(UserSession us, WebPart part) {
		Map<String, Object> args = us.getSavedArgs();

		String photoId = us.getAndSaveAsString(args, "photoId");

		Photo photo = PhotoManager.getPhoto(photoId);
		part.addString(Photo.THUMB, getPhotoThumb(us, photo));

		part.addString("photoId", photoId);
		part.addString(Photo.ID, photo.getId().asString());
		part.addSelect(Photo.STATUS, PhotoStatus.class, (String) args.get(Photo.STATUS));
		part.maskAndAddStringFromArgsWithDefault(args, Photo.TAGS, photo.getTags().asString());
	}
	
	/**
	 * 
	 */
	protected String doHandlePost(UserSession us, Map args) {
		String id = us.getAndSaveAsString(args, "photoId");
		Photo photo = PhotoManager.getPhoto(id);
	
		String tags = us.getAndSaveAsString(args, Photo.TAGS);
		photo.setTags(new Tags(tags));
		String status = us.getAndSaveAsString(args, Photo.STATUS);
		photo.setStatus(PhotoStatus.getFromString(status));
		
		double lat=0.0;
		double lon=0.0;
		
		try {
			lat = Double.parseDouble(us.getAndSaveAsString(args, Photo.LAT));
			lon = Double.parseDouble(us.getAndSaveAsString(args, Photo.LON));
		}
		catch(Exception e)
		{
			lat=0.0;
			lon = 0.0;
		}
		
		String mapcode = us.getAndSaveAsString(args, Photo.MAPCODE);
		Location location;
		location = new GPSLocation(lat, lon);
		
		if(lat == 0.0 && lon == 0.0 && mapcode != "") {
			location.setMapcode(mapcode);
		}
		photo.setLocation(location);
		
		doHandleLandscapePhotoPost( photo, us, args);
		
		PhotoManager pm = PhotoManager.getInstance();
		pm.savePhoto(photo);
		
		StringBuffer sb = UserLog.createActionEntry("AdminUserPhoto");
		UserLog.addUpdatedObject(sb, "Photo", photo.getId().asString());
		UserLog.log(sb);
		
		us.setMessage(us.cfg().getPhotoUpdateSucceeded());

		return PartUtil.SHOW_ADMIN_PAGE_NAME;
	}
	
	public void doHandleLandscapePhotoPost(Photo photo, UserSession us, Map args){
		if(photo instanceof LandscapePhoto){
		try{	
			String landscapeID = us.getAndSaveAsString(args, "landscapeID");
			
			boolean mountain = Boolean.parseBoolean(us.getAndSaveAsString(args, "mountains"));
			boolean dessert = Boolean.parseBoolean(us.getAndSaveAsString(args, "dessert"));
			boolean ocean = Boolean.parseBoolean(us.getAndSaveAsString(args, "ocean"));
			boolean steppe = Boolean.parseBoolean(us.getAndSaveAsString(args, "steppe"));
			boolean beach = Boolean.parseBoolean(us.getAndSaveAsString(args, "beach"));
			boolean countryside = Boolean.parseBoolean(us.getAndSaveAsString(args, "countryside"));
			boolean forest = Boolean.parseBoolean(us.getAndSaveAsString(args, "forest"));
			LandscapeType landscapeType = new LandscapeType(mountain,forest ,dessert, countryside, beach, steppe, ocean);
			
			LandscapePhotoFilterEnum filter = LandscapePhotoFilterEnum.valueOf(us.getAndSaveAsString(args, "filter"));
			
			LandscapeManager lm = LandscapeManager.getInstance();
			Landscape landscape = lm.createLandscape();
			landscape.setLandscapeType(landscapeType);
			landscape.setLandscapePhotoFilterEnum(filter);
			
			LandscapePhoto landscapePhoto = (LandscapePhoto)photo;
			landscapePhoto.setLandscape(landscape);
			
			lm.saveLandscape(landscape);
		}	
		catch(Exception e)
		{}
		}
	}
	
}
