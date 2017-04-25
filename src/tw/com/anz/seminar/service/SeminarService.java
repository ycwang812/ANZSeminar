package tw.com.anz.seminar.service;

import java.util.List;

import tw.com.anz.seminar.model.Event;
import tw.com.anz.seminar.model.Question;
import tw.com.anz.seminar.model.Report;

public interface SeminarService {
	
	public Event queryEvent(String eventId);
	
	public String queryYouTubeId(long id);

	public void saveQuestion(Question question);
	
	public void saveOrUpdateReport(Report report);
	
	public Event queryHeadlineEvent();
	
	public List<Event> findRecentRecordEvent();

}
