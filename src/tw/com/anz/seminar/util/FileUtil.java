package tw.com.anz.seminar.util;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import tw.com.anz.seminar.model.Event;

public class FileUtil {
	
	private static Logger log = Logger.getLogger(FileUtil.class);
	
	public static String ICS_FILE_NAME;
	public static String IMAGE_PATH;
	public static String FILE_PATH;
	
	public static String getFilename(String fullname) {
		fullname = fullname.replace('\\', '/' );
		StringTokenizer token = new StringTokenizer(fullname, "/");
		String filename = null;
		
		while (token.hasMoreTokens()) {
			filename = token.nextToken();
		}
		
		String[] s = filename.split("[.]");
		
		filename = System.currentTimeMillis() + "." + s[s.length - 1];

		return filename;
	}
	
	public static String genIcsFile(Event event) {
		StringBuilder sb = new StringBuilder();
		sb.append("BEGIN:VCALENDAR").append("\n");
		sb.append("PRODID:-//Google Inc//Google Calendar 70.9054//EN").append("\n");
		sb.append("VERSION:2.0").append("\n");
		sb.append("CALSCALE:GREGORIAN").append("\n");
		sb.append("METHOD:REQUEST").append("\n");
		sb.append("BEGIN:VEVENT").append("\n");
		sb.append("DTSTART:").append(DateUtil.formatIcsDateTime(event.getStartTime())).append("\n");
		sb.append("DTEND:").append(DateUtil.formatIcsDateTime(event.getEndTime())).append("\n");
		sb.append("DTSTAMP:").append(DateUtil.formatIcsDateTime(event.getCreateTime())).append("\n");
		sb.append("UID:").append(event.getEventId()).append("@google.com").append("\n");
		sb.append("CREATED:").append(DateUtil.formatIcsDateTime(event.getCreateTime())).append("\n");
		sb.append("URL:").append(event.getEventUrl()).append("\n");
		sb.append("DESCRIPTION:").append("\n");
		
		if (event.getUpdateTime() != null) {
			sb.append("LAST-MODIFIED:").append(DateUtil.formatIcsDateTime(event.getUpdateTime())).append("\n");
		}
		
		sb.append("LOCATION:").append("\n");
		sb.append("SEQUENCE:0").append("\n");
		sb.append("STATUS:CONFIRMED").append("\n");
		sb.append("SUMMARY:").append(event.getSummary()).append("\n");
		sb.append("TRANSP:OPAQUE").append("\n");
		sb.append("END:VEVENT").append("\n");
		sb.append("END:VCALENDAR").append("\n");
		
		return sb.toString();
	}

	public static void setIcsFileName(String icsFileName) {
		ICS_FILE_NAME = icsFileName;
	}

	public static void setImagePath(String imagePath) {
		IMAGE_PATH = imagePath;
	}

	public static void setFilePath(String filePath) {
		FILE_PATH = filePath;
	}

}
