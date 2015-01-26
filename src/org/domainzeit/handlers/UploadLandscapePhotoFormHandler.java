package org.domainzeit.handlers;

import java.io.File;
import java.util.Map;






import org.domainzeit.exception.LocationException;
import org.domainzeit.model.*;
import org.domainzeit.model.location.*;
import org.wahlzeit.handlers.PartUtil;
import org.wahlzeit.handlers.UploadPhotoFormHandler;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.Tags;
import org.wahlzeit.model.User;
import org.wahlzeit.model.UserLog;
import org.wahlzeit.model.UserSession;
import org.wahlzeit.services.SysConfig;
import org.wahlzeit.services.SysLog;
import org.wahlzeit.utils.StringUtil;

public class UploadLandscapePhotoFormHandler extends UploadPhotoFormHandler{
	
	/**
	 * 
	 */
	protected String doHandlePost(UserSession us, Map args) {
		String tags = us.getAndSaveAsString(args, Photo.TAGS);

		double lat = 0.0;
		double lon = 0.0;



		try {
			try {
				lat = Double.parseDouble(us.getAndSaveAsString(args, Photo.LAT));
				lon = Double.parseDouble(us.getAndSaveAsString(args, Photo.LON));
			} catch (Exception e) {
				lat = 0.0;
				lon = 0.0;
			}

			String mapcode = us.getAndSaveAsString(args, Photo.MAPCODE);
			Location location;
			location = new GPSLocation(lat, lon);

			if (lat == 0.0 && lon == 0.0 && mapcode != "") {
				location.setMapcode(mapcode);
			}

			if (!StringUtil.isLegalTagsString(tags)) {
				us.setMessage(us.cfg().getInputIsInvalid());
				return PartUtil.UPLOAD_PHOTO_PAGE_NAME;
			}
			
			PhotoManager pm = PhotoManager.getInstance();
			String sourceFileName = us.getAsString(args, "fileName");
			File file = new File(sourceFileName);
			Photo photo = pm.createPhoto(file);

			String targetFileName = SysConfig.getBackupDir().asString()
					+ photo.getId().asString();
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

			us.setTwoLineMessage(us.cfg().getPhotoUploadSucceeded(), us.cfg()
					.getKeepGoing());
		} catch(LocationException locEx) {
			SysLog.logThrowable(locEx);
			us.setMessage(us.cfg().getPhotoUploadFailedLocation());
		} catch (Exception ex) {
			SysLog.logThrowable(ex);
			us.setMessage(us.cfg().getPhotoUploadFailed());
		}

		return PartUtil.UPLOAD_PHOTO_PAGE_NAME;
	}

	public void doHandleLandscapePhotoPost(Photo photo, UserSession us, Map args) {
		if (photo instanceof LandscapePhoto) {
			try {
				String landscapeID = us
						.getAndSaveAsString(args, "landscape_id");
				String name = us.getAndSaveAsString(args, "name");
				boolean mountain = Boolean.parseBoolean(us.getAndSaveAsString(
						args, "mountains"));
				boolean dessert = Boolean.parseBoolean(us.getAndSaveAsString(
						args, "dessert"));
				boolean ocean = Boolean.parseBoolean(us.getAndSaveAsString(
						args, "ocean"));
				boolean steppe = Boolean.parseBoolean(us.getAndSaveAsString(
						args, "steppe"));
				boolean beach = Boolean.parseBoolean(us.getAndSaveAsString(
						args, "beach"));
				boolean countryside = Boolean.parseBoolean(us
						.getAndSaveAsString(args, "countryside"));
				boolean forest = Boolean.parseBoolean(us.getAndSaveAsString(
						args, "forest"));
				LandscapeStyle landscapeStyle = new LandscapeStyle(mountain,
						forest, dessert, countryside, beach, steppe, ocean);

				LandscapePhotoFilterEnum filter = LandscapePhotoFilterEnum
						.valueOf(us.getAndSaveAsString(args, "filter"));

				LandscapeManager lm = LandscapeManager.getInstance();
				LandscapeType landscapeType = new LandscapeType();
				Landscape landscape = lm.createLandscape();
				landscape.setName(name);
				landscape.setLandscapeType(landscapeType);
				landscape.getType().setLandscapeStyle(landscapeStyle);
				landscape.getType().setLandscapePhotoFilterEnum(filter);

				LandscapePhoto landscapePhoto = (LandscapePhoto) photo;
				landscapePhoto.setLandscape(landscape);
				lm.saveLandscape(landscape);
			} catch (Exception e) {
			}
		}
	}
}
