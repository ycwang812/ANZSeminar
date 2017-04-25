package tw.com.anz.seminar.service;

import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import tw.com.anz.seminar.Constant;
import tw.com.anz.seminar.util.UrlUtil;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.YouTube.Videos;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

public class GoogleApiServiceImpl implements GoogleApiService {

	private Logger log = Logger.getLogger(getClass());
	
	private String applicationName;
	private String serviceAccountEmail;
	private String serviceAccountPrivateKey;
	private String serviceAccountUser;
	
	private static HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	
	public String createEvent(tw.com.anz.seminar.model.Event event) {
		Calendar calendar = getCalendarService();
		Event googleEvent = setGoogleEvent(event, null);
		String eventId = null;
		
		try {
			Event createEvent = calendar.events().insert(Constant.CALENDAR_ID, googleEvent).execute();
			eventId = createEvent.getId();
			
			if (event.getSeminarKind().equals(Constant.RECORD) && (event.getYoutubeId() == null || event.getYoutubeId().isEmpty())) {
				return eventId;
			}
			
			String url = UrlUtil.getWatchUrl(eventId);
			event.setEventUrl(url);
			createEvent.setDescription(UrlUtil.getATag(url, false));
			calendar.events().update(Constant.CALENDAR_ID, eventId, createEvent).execute();
		} catch (Exception ex) {
			log.error("createEvent", ex);
		}
		
		return eventId;
	}
	
	public void updateEvent(tw.com.anz.seminar.model.Event event) {
		Calendar calendar = getCalendarService();
		
		try {
			Event googleEvent = calendar.events().get(Constant.CALENDAR_ID, event.getEventId()).execute();
			googleEvent = setGoogleEvent(event, googleEvent);
			calendar.events().update(Constant.CALENDAR_ID, event.getEventId(), googleEvent).execute();
		} catch (Exception ex) {
			log.error("updateEvent", ex);
		}
	}
	
	public void deleteEvent(String eventId) {
		Calendar calendar = getCalendarService();
		
		try {
			calendar.events().delete(Constant.CALENDAR_ID, eventId).execute();
		} catch (Exception ex) {
			log.error("deleteEvent", ex);
		}
	}
	
	private Event setGoogleEvent(tw.com.anz.seminar.model.Event event, Event googleEvent) {
		DateTime start = new DateTime(event.getStartTime(), TimeZone.getTimeZone(Constant.TIME_ZONE_TW));
		DateTime end = new DateTime(event.getEndTime(), TimeZone.getTimeZone(Constant.TIME_ZONE_TW));
		
		if (googleEvent == null) {
			googleEvent = new Event();
		}

		googleEvent.setSummary(event.getSummary());
		googleEvent.setStart(new EventDateTime().setDateTime(start));
		googleEvent.setEnd(new EventDateTime().setDateTime(end));
		
		if (event.getSeminarKind().equals(Constant.RECORD) && (event.getYoutubeId() == null || event.getYoutubeId().isEmpty())) {
			event.setEventUrl(event.getUrl());
			googleEvent.setDescription(UrlUtil.getATag(event.getUrl(), true));
		} else if (event.getEventId() != null && !event.getEventId().isEmpty()) {
			String url = UrlUtil.getWatchUrl(event.getEventId());
			event.setEventUrl(url);
			googleEvent.setDescription(UrlUtil.getATag(url, false));
		}
		
		return googleEvent;
	}
	
	public void changeYouTubePrivacy(String youtubeId, String status) {
		YouTube youtube = getYouTubeService();
		Videos videos = youtube.videos();
		
		try {
			VideoListResponse videoListResponse = videos.list(youtubeId, "status").execute();
			Video video = videoListResponse.getItems().get(0);
			video.getStatus().setPrivacyStatus(status);
			videos.update("status", video).execute();
		} catch (Exception ex) {
			log.error("changeYouTubePrivacy", ex);
		}
	}
	
	private Calendar getCalendarService() {
		Calendar calendar = null;
		
		try {
			Credential credential = authorize(CalendarScopes.CALENDAR);
			
			calendar = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(applicationName)
				.build();
		} catch (Exception ex) {
			log.error("getCalendarService", ex);
		}
		
		return calendar;
	}
	
	private YouTube getYouTubeService() {
		YouTube youTube = null;
		
		try {
			Credential credential = authorize(YouTubeScopes.YOUTUBE);
			
			youTube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(applicationName)
				.build();
		} catch (Exception ex) {
			log.error("getCalendarService", ex);
		}
		
		return youTube;
	}
	
	private Credential authorize(String scopes) throws Exception {		
		GoogleCredential credential = new GoogleCredential.Builder()
			.setTransport(HTTP_TRANSPORT)
			.setJsonFactory(JSON_FACTORY)
			.setServiceAccountId(serviceAccountEmail)
			.setServiceAccountScopes(scopes)
			.setServiceAccountPrivateKeyFromP12File(new ClassPathResource(serviceAccountPrivateKey).getFile())
			.setServiceAccountUser(serviceAccountUser)
			.build();
		 return credential;
	 }
	
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	
	public void setServiceAccountEmail(String serviceAccountEmail) {
		this.serviceAccountEmail = serviceAccountEmail;
	}
	
	public void setServiceAccountPrivateKey(String serviceAccountPrivateKey) {
		this.serviceAccountPrivateKey = serviceAccountPrivateKey;
	}
	
	public void setServiceAccountUser(String serviceAccountUser) {
		this.serviceAccountUser = serviceAccountUser;
	}
	
}
