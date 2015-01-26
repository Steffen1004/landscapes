package org.domainzeit.handlers;

import org.domainzeit.model.LandscapePhoto;
import org.wahlzeit.handlers.PartUtil;
import org.wahlzeit.handlers.ShowPhotoPageHandler;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.UserSession;
import org.wahlzeit.webparts.WebPart;

public class ShowLandscapePhotoPageHandler extends ShowPhotoPageHandler{
	
	/**
	 * 
	 */
	protected void makePhotoCaption(UserSession us, WebPart page) {
		Photo photo = us.getPhoto();
			
		WebPart caption = createWebPart(us, PartUtil.CAPTION_INFO_FILE);
		caption.addString(Photo.CAPTION, getPhotoCaption(us, photo));
		caption.addString(Photo.LOCATION, photo.getLocation());
		makeLandscapePhotoCaption(photo, caption);

		page.addWritable(Photo.CAPTION, caption);
	}
	
	public void makeLandscapePhotoCaption(Photo photo, WebPart caption){
		if(photo instanceof LandscapePhoto){
			LandscapePhoto landscapePhoto = (LandscapePhoto)photo;
			
			caption.addString("name", ((LandscapePhoto)photo).getLandscape().getName());
			caption.addString("type", ((LandscapePhoto)photo).getLandscape().getType().getLandscapeStyle().asString());
			caption.addString("filter", ((LandscapePhoto)photo).getLandscape().getType().getLandscapePhotoFilterEnum().name());
		}
	}

}
