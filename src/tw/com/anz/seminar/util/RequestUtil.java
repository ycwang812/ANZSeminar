package tw.com.anz.seminar.util;

import javax.servlet.ServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.ServletRequestUtils;

import tw.com.anz.seminar.Constant;
import tw.com.anz.seminar.model.Event;

public class RequestUtil {
	
	private static Logger log = Logger.getLogger(RequestUtil.class);
	
	public static Event getEventParameter(ServletRequest request) {
		Event event = new Event();
		
		long id = ServletRequestUtils.getLongParameter(request, "id", 0);
		
		if (id != 0) {
			event.setId(id);
		}
		
		event.setSummary(ServletRequestUtils.getStringParameter(request, "summary", ""));
		
		String eventId = ServletRequestUtils.getStringParameter(request, "eventId", "");
		
		if (!eventId.isEmpty()) {
			event.setEventId(eventId);
		}
		
		event.setStartTime(DateUtil.parseDateTime(ServletRequestUtils.getStringParameter(request, "startDate", ""), ServletRequestUtils.getStringParameter(request, "startTime", "")));
		event.setEndTime(DateUtil.parseDateTime(ServletRequestUtils.getStringParameter(request, "endDate", ""), ServletRequestUtils.getStringParameter(request, "endTime", "")));
		event.setTimeZone(Constant.TIME_ZONE_TW);
		
		String introduction = ServletRequestUtils.getStringParameter(request, "introduction", "");
		
		if (!introduction.isEmpty()) {
			event.setIntroduction(introduction);
		}
		
		String speaker = ServletRequestUtils.getStringParameter(request, "speaker", "");
		
		if (!speaker.isEmpty()) {
			event.setSpeaker(speaker);
		}
		
		event.setSeminarKind(ServletRequestUtils.getStringParameter(request, "seminarKind", Constant.HANGOUT));
		
		if (event.getSeminarKind().equals(Constant.HANGOUT)) {
			String youtubeUrl = ServletRequestUtils.getStringParameter(request, "youtubeUrl", "");
			
			if (!youtubeUrl.isEmpty()) {
				event.setYoutubeUrl(youtubeUrl);
			}
			
			event.setPrivacyStatus(ServletRequestUtils.getStringParameter(request, "privacyStatus", Constant.PRIVACY_UNLISTED));
			event.setQuestion(ServletRequestUtils.getStringParameter(request, "question", Constant.NO));
		} else {
			event.setUrl(ServletRequestUtils.getStringParameter(request, "url", ""));
		}
		
		event.setUploadImage(ServletRequestUtils.getStringParameter(request, "uploadImage", ""));
		
		String legal = ServletRequestUtils.getStringParameter(request, "legal", "");
		
		if (!legal.isEmpty()) {
			event.setLegal(legal);
		}
		
		String uploadFile = ServletRequestUtils.getStringParameter(request, "uploadFile", "");
		
		if (!uploadFile.isEmpty()) {
			event.setUploadFile(uploadFile);
		}
		
		String duration = ServletRequestUtils.getStringParameter(request, "duration", "");
		
		if (!duration.isEmpty()) {
			event.setDuration(duration);
		}
		
		String headline = ServletRequestUtils.getStringParameter(request, "headline", "");
		
		if (!headline.isEmpty()) {
			event.setHeadline(headline);
		}
		
		String top = ServletRequestUtils.getStringParameter(request, "top", "");
		
		if (!top.isEmpty()) {
			event.setTop(top);
		}
		
		event.setLogin(ServletRequestUtils.getStringParameter(request, "login", Constant.NO));

		return event;
	}

}
