package tw.com.anz.seminar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import tw.com.anz.seminar.Constant;

import com.google.api.client.util.DateTime;

public class DateUtil {

	private static Logger log = Logger.getLogger(DateUtil.class);
	
	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat slashDateTimeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	
	public static Date parseDateTime(String d, String t) {
		Date date = null;
		try {
			date = dateTimeFormat.parse(d + " " + t);
		} catch (ParseException ex) {
			log.error("Parse Date Error: ", ex);
		}
		return date;
	}
	
	public static String formatDateTime(Date date) {
		return dateTimeFormat.format(date);
	}
	
	public static String formatSlashDateTime(Date date) {
		return slashDateTimeFormat.format(date);
	}
	
	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}
	
	public static String formatTime(Date date) {
		return timeFormat.format(date);
	}
	
	public static String parseSecToTime(long second) {
		if (second == 0) {
			return "0";
		}
		
		int hours = (int) second / 3600;
	    int remainder = (int) second - hours * 3600;
	    int mins = remainder / 60;
	    remainder = remainder - mins * 60;
	    int secs = remainder;
		
		return hours + ":" + mins + ":" + secs;
	}
	
	public static Date getFirstDayOfWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	public static Date getLastDayOfWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}
	
	public static Date getFirstDayOfMonth() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}
	
	public static Date getLastDayOfMonth() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}
	
	public static String formatIcsDateTime(Date date) {
		DateTime dateTime = new DateTime(date, TimeZone.getTimeZone(Constant.TIME_ZONE_GMT));
		return dateTime.toStringRfc3339().replaceAll("-", "").replaceAll(":", "").substring(0, 15) + "Z";
	}
	
}
