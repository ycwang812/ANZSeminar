package tw.com.anz.seminar.service;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import tw.com.anz.seminar.util.UrlUtil;

public class CalendarServiceImpl implements CalendarService {
	
	private Logger log = Logger.getLogger(getClass());
	
	private String account;
	private String cssFile = "calendar.css";
	
	public String parseEmbedHtml() {
		Document doc;
		
		try {
			doc = Jsoup.connect(UrlUtil.getCalendarUrl(account)).get();
			doc.select("link").attr("href", cssFile);
			
			Element element = doc.select("script").get(1);
			element.attr("src", UrlUtil.GOOGLE_CALENDAR_URL + element.attr("src"));
		} catch (Exception ex) {
			log.error("parseEmbedHtml", ex);
			return null;
		}
		
		return doc.html();
	}
	
	public void setAccount(String account) {
		this.account = account;
	}

}
