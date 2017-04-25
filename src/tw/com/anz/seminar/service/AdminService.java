package tw.com.anz.seminar.service;

import java.util.List;

import tw.com.anz.seminar.model.Event;
import tw.com.anz.seminar.model.Question;
import tw.com.anz.seminar.model.Report;

public interface AdminService {

	public void saveEvent(Event event, String username);
	
	public void updateEvent(Event event, String username);
	
	public void deleteEvent(long id, String eventId);
	
	public Event queryEvent(long id);
	
	public List<Event> getAllEvent();
	
	public List<Question> queryQuestion(long eId);
	
	public long countViews(long eId, String condition);
	
	public String sumWatchTime(long eId, String condition);
	
	public String averageWatchTime(String sum, long times);
	
	public List<Report> queryTop10Report(long eId);
	
	public List<Report> queryReport(long eId);
	
	public void clearTop();
	
}
