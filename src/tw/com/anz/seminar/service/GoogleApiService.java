package tw.com.anz.seminar.service;

import tw.com.anz.seminar.model.Event;

public interface GoogleApiService {
	
	public String createEvent(Event event);
	
	public void updateEvent(tw.com.anz.seminar.model.Event event);
	
	public void deleteEvent(String eventId);
	
	public void changeYouTubePrivacy(String youtubeId, String status);

}
