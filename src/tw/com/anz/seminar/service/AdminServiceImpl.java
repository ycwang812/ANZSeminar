package tw.com.anz.seminar.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import tw.com.anz.seminar.Constant;
import tw.com.anz.seminar.dao.EventDao;
import tw.com.anz.seminar.dao.QuestionDao;
import tw.com.anz.seminar.dao.ReportDao;
import tw.com.anz.seminar.model.Event;
import tw.com.anz.seminar.model.Question;
import tw.com.anz.seminar.model.Report;
import tw.com.anz.seminar.util.DateUtil;
import tw.com.anz.seminar.util.UrlUtil;

public class AdminServiceImpl implements AdminService {
	
	private Logger log = Logger.getLogger(getClass());
	
	private GoogleApiService googleApiService;
	private EventDao eventDao;
	private ReportDao reportDao;
	private QuestionDao questionDao;
	
	public void saveEvent(Event event, String username) {
		setYoutubeId(event);
		String eventId = googleApiService.createEvent(event);
		
		if (eventId == null) {
			return;
		}
		
		if (event.getSeminarKind().equals(Constant.HANGOUT) && event.getYoutubeId() != null && !event.getYoutubeId().isEmpty()) {
			googleApiService.changeYouTubePrivacy(event.getYoutubeId(), event.getPrivacyStatus());
		}
		
		if (event.getHeadline() != null && event.getHeadline().equals(Constant.YES)) {
			checkAndClearHeadline();
		}
		
		event.setEventId(eventId);
		event.setUsername(username);
		event.setCreateTime(new Date());
		
		eventDao.save(event);
	}
	
	public void updateEvent(Event event, String username) {
		setYoutubeId(event);
		googleApiService.updateEvent(event);
		
		if (event.getSeminarKind().equals(Constant.HANGOUT) && event.getYoutubeId() != null && !event.getYoutubeId().isEmpty()) {
			googleApiService.changeYouTubePrivacy(event.getYoutubeId(), event.getPrivacyStatus());
		}
		
		if (event.getHeadline() != null && event.getHeadline().equals(Constant.YES)) {
			checkAndClearHeadline();
		}
		
		event.setUsername(username);
		event.setUpdateTime(new Date());
		
		Event oldEvent = eventDao.findEventById(event.getId());
		BeanUtils.copyProperties(event, oldEvent, new String[] { "id", "eventId", "timeZone", "createTime" });
		
		eventDao.update(oldEvent);
	}
	
	public void deleteEvent(long id, String eventId) {
		googleApiService.deleteEvent(eventId);
		reportDao.deleteByEId(id);
		questionDao.deleteByEId(id);
		eventDao.deleteById(id);
	}
	
	private void setYoutubeId(Event event) {
		if (event.getSeminarKind().equals(Constant.HANGOUT) && (event.getYoutubeUrl() != null && !event.getYoutubeUrl().isEmpty())) {
			event.setYoutubeId(UrlUtil.getYoutubeId(event.getYoutubeUrl()));
		} else if (event.getSeminarKind().equals(Constant.RECORD) && UrlUtil.isYouTubeId(event.getUrl())) {
			event.setYoutubeId(UrlUtil.getYoutubeId(event.getUrl()));
		}
	}
	
	private void checkAndClearHeadline() {
		Event event = eventDao.findByHeadline();
		
		if (event == null) {
			return;
		}
		
		event.setHeadline(null);
		eventDao.save(event);
	}
	
	public Event queryEvent(long id) {
		return eventDao.findEventById(id);
	}
	
	public List<Event> getAllEvent() {
		List<Event> list = eventDao.getAllOrderByIdDesc();
		return list != null ? list : new ArrayList<Event>();
	}
	
	public List<Question> queryQuestion(long eId) {
		List<Question> list = questionDao.findByEId(eId);
		return list != null ? list : new ArrayList<Question>();
	}
	
	public long countViews(long eId, String condition) {
		long count = 0;
		
		if (condition == null || condition.equals(Constant.TOTAL)) {
			count =  reportDao.countViews(eId);
		} else if (condition.equals(Constant.MONTH)) {
			count = reportDao.countViews(eId, DateUtil.getFirstDayOfMonth(), DateUtil.getLastDayOfMonth());
		} else {
			count = reportDao.countViews(eId, DateUtil.getFirstDayOfWeek(), DateUtil.getLastDayOfWeek());
		}
		
		return count;
	}
	
	public String sumWatchTime(long eId, String condition) {
		long count = 0;
		
		if (condition == null || condition.equals(Constant.TOTAL)) {
			count = reportDao.sumWatchTime(eId);
		} else if (condition.equals(Constant.MONTH)) {
			count = reportDao.sumWatchTime(eId, DateUtil.getFirstDayOfMonth(), DateUtil.getLastDayOfMonth());
		} else {
			count = reportDao.sumWatchTime(eId, DateUtil.getFirstDayOfWeek(), DateUtil.getLastDayOfWeek());
		}
		
		if (count == 0) {
			return "0";
		}
		
		BigDecimal b = new BigDecimal(String.valueOf(count));
		return b.divide(new BigDecimal(60), 0, BigDecimal.ROUND_CEILING).toString();
	}
	
	public String averageWatchTime(String sum, long times) {
		if (sum.equals("0")) {
			return "0";
		}
		
		BigDecimal b1 = new BigDecimal(String.valueOf(sum));
		BigDecimal b2 = new BigDecimal(String.valueOf(times));
		return b1.divide(b2, 0, BigDecimal.ROUND_CEILING).toString();
	}
	
	public List<Report> queryTop10Report(long eId) {
		List<Report> list = reportDao.findByEIdOrderByOpenTime(eId);
		
		if (list == null) {
			return new ArrayList<Report>();
		} else if (list.size() <= 10) {
			return list;
		}
		
		List<Report> newList = new ArrayList<Report>();
		
		for (int i = 0; i < 10; i++) {
			newList.add(list.get(i));
		}
		
		return newList;
	}
	
	public List<Report> queryReport(long eId) {
		List<Report> list = reportDao.findByEId(eId);
		return list != null ? list : new ArrayList<Report>();
	}
	
	public void clearTop() {
		List<Event> list = eventDao.findByTopOrderByStartTimeDesc(true);
		
		if (list == null) {
			return;
		}
		
		log.debug("Clear top amount: " + list.size());
		
		for (Event event : list) {
			event.setTop(null);
		}
		
		eventDao.saveOrUpdateAll(list);
	}

	public void setGoogleApiService(GoogleApiService googleApiService) {
		this.googleApiService = googleApiService;
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
