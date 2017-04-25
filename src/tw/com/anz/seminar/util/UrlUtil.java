package tw.com.anz.seminar.util;

import org.apache.log4j.Logger;

import tw.com.anz.seminar.Constant;

public class UrlUtil {

	private static Logger log = Logger.getLogger(FileUtil.class);
	
	public static String SEMINAR_URL;
	public static final String GOOGLE_CALENDAR_URL = "https://www.google.com/calendar/";
	private static final String YOUTUBE_URL_1 = "http:[/][/]youtu[.]be[/]";
	private static final String YOUTUBE_URL_2 = "http:[/][/]www[.]youtube[.]com[/]watch[?]v=";
	
	public static String getATag(String url, boolean blank) {
		StringBuilder sb = new StringBuilder();
		sb.append("<a href='").append(url).append(blank ? "' target='_blank'>" : "'>").append(url).append("</a>");
		return sb.toString();
	}
	
	public static String getWatchUrl(String id) {
		StringBuilder sb = new StringBuilder();
		sb.append(SEMINAR_URL).append(Constant.PROJECT_NAME).append(Constant.WATCH_URL).append(id);
		return sb.toString();
	}
	
	public static String getIcsUrl(String id) {
		StringBuilder sb = new StringBuilder();
		sb.append(SEMINAR_URL).append(Constant.PROJECT_NAME).append(Constant.ICS_URL).append(id);
		return sb.toString();
	}
	
	public static String getYoutubeId(String url) {
		String youtubeId = null;
		
		if (url.matches("^" + YOUTUBE_URL_1 + ".*")) {
			youtubeId = url.replaceFirst(YOUTUBE_URL_1, "");
		} else if (url.matches("^" + YOUTUBE_URL_2 + ".*")) {
			youtubeId = url.replaceFirst(YOUTUBE_URL_2, "");
		}
		
		return youtubeId;
	}
	
	public static boolean isYouTubeId(String url) {
		if (url.matches("^" + YOUTUBE_URL_1 + ".*")) {
			return true;
		} else if (url.matches("^" + YOUTUBE_URL_2 + ".*")) {
			return true;
		}
		
		return false;
	}
	
	public static String getCalendarUrl(String account) {
		StringBuilder sb = new StringBuilder();
		sb.append(GOOGLE_CALENDAR_URL).append("embed?");
		sb.append("showTitle=0").append("&");
		sb.append("showNav=0").append("&");	
		sb.append("showPrint=0").append("&");
		sb.append("showCalendars=0").append("&");
		sb.append("showTz=0").append("&");
		sb.append("height=450").append("&");
		sb.append("wkst=1").append("&");
		sb.append("src=").append(account).append("&");
		sb.append("ctz=Asia%2FTaipei");
		return sb.toString();
	}
	
	public static String getUrl() {
		return SEMINAR_URL + Constant.PROJECT_NAME;
	}

	public static void setSeminarUrl(String seminarUrl) {
		SEMINAR_URL = seminarUrl;
	}

}
