package tw.com.anz.seminar.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import tw.com.anz.seminar.dao.EventDao;
import tw.com.anz.seminar.dao.QuestionDao;
import tw.com.anz.seminar.dao.ReportDao;
import tw.com.anz.seminar.model.Event;
import tw.com.anz.seminar.model.Question;
import tw.com.anz.seminar.model.Report;

public class SeminarServiceImpl implements SeminarService {
	
	private Logger log = Logger.getLogger(getClass());
	
	private EventDao eventDao;
	private ReportDao reportDao;
	private QuestionDao questionDao;
	
	public Event queryEvent(String eventId) {
		return eventDao.findByEventId(eventId);
	}
	
	public String queryYouTubeId(long id) {
		Event event = (Event) eventDao.findById(id);
		return event.getYoutubeId() != null && !event.getYoutubeId().isEmpty() ? event.getYoutubeId() : "";
	}
	
	public void saveQuestion(Question question) {
		questionDao.save(question);
	}
	
	public void saveOrUpdateReport(Report report) {
		long watchTime = reportDao.getWatchTime(report.getId());
		
		if (watchTime == -1) {
			reportDao.save(report);
		} else if (report.getWatchTime() > watchTime) {
			reportDao.update(report);
		}
	}
	
	public Event queryHeadlineEvent() {
		Event event = eventDao.findByHeadline();
		
		if (event != null) {
			return event;
		}
		
		List<Event> list = eventDao.getAllOrderByStartTimeDesc();
		
		return list != null ? list.get(0) : null;
	}
	
	public List<Event> findRecentRecordEvent() {
		List<Event> topList = eventDao.findByTopOrderByStartTimeDesc(true);
		
		if (topList == null)  {
			topList = new ArrayList<Event>();
		}
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -3);
		
		List<Event> list = eventDao.findByNoTopAndStartTimeOrderByStartTimeDesc(c.getTime());
		
		if (list != null) {
			topList.addAll(list);
		}
		
		return topList;
	}

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	
	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}
	
}
