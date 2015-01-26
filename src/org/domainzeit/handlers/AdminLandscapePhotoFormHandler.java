package org.domainzeit.handlers;

import java.util.Map;

import org.domainzeit.exception.LocationException;
import org.domainzeit.model.Landscape;
import org.domainzeit.model.LandscapeManager;
import org.domainzeit.model.LandscapePhoto;
import org.domainzeit.model.LandscapePhotoFilterEnum;
import org.domainzeit.model.LandscapeStyle;
import org.domainzeit.model.location.GPSLocation;
import org.domainzeit.model.location.Location;
import org.wahlzeit.handlers.AdminUserPhotoFormHandler;
import org.wahlzeit.handlers.PartUtil;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.PhotoStatus;
import org.wahlzeit.model.Tags;
import org.wahlzeit.model.UserLog;
import org.wahlzeit.model.UserSession;
import org.wahlzeit.services.SysLog;

public class AdminLandscapePhotoFormHandler extends AdminUserPhotoFormHandler{
	
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
		try {
			location = new GPSLocation(lat, lon);
			
			if(lat == 0.0 && lon == 0.0 && mapcode != "") {
				location.setMapcode(mapcode);
			}
			photo.setLocation(location);
		} catch (LocationException locEx) {
			SysLog.logThrowable(locEx);
			us.setMessage(us.cfg().getPhotoUploadFailedLocation());	
		}
	
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
			String landscapeID = us.getAndSaveAsString(args, "landscape_id");
			String name = us.getAndSaveAsString(args, "name");	
			boolean mountain = Boolean.parseBoolean(us.getAndSaveAsString(args, "mountains"));
			boolean dessert = Boolean.parseBoolean(us.getAndSaveAsString(args, "dessert"));
			boolean ocean = Boolean.parseBoolean(us.getAndSaveAsString(args, "ocean"));
			boolean steppe = Boolean.parseBoolean(us.getAndSaveAsString(args, "steppe"));
			boolean beach = Boolean.parseBoolean(us.getAndSaveAsString(args, "beach"));
			boolean countryside = Boolean.parseBoolean(us.getAndSaveAsString(args, "countryside"));
			boolean forest = Boolean.parseBoolean(us.getAndSaveAsString(args, "forest"));
			LandscapeStyle landscapeStyle = new LandscapeStyle(mountain,forest ,dessert, countryside, beach, steppe, ocean);
			
			LandscapePhotoFilterEnum filter = LandscapePhotoFilterEnum.valueOf(us.getAndSaveAsString(args, "filter"));
			
			LandscapeManager lm = LandscapeManager.getInstance();
			Landscape landscape = lm.createLandscape();
			landscape.setName(name);
			landscape.getType().setLandscapeStyle(landscapeStyle);
			landscape.getType().setLandscapePhotoFilterEnum(filter);
			
			LandscapePhoto landscapePhoto = (LandscapePhoto)photo;
			landscapePhoto.setLandscape(landscape);
			
			lm.saveLandscape(landscape);
		}	
		catch(Exception e)
		{}
		}
	}
}