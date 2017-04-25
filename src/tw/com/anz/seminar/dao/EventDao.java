package tw.com.anz.seminar.dao;

import java.util.Date;
import java.util.List;

import tw.com.anz.seminar.model.Event;

public interface EventDao extends BaseDao {
	
	public List<Event> getAllOrderByIdDesc();

	public Event findEventById(long id);
	
	public Event findByEventId(String eventId);
	
	public Event findByHeadline();
	
	public List<Event> getAllOrderByStartTimeDesc();
	
	public List<Event> findByTopOrderByStartTimeDesc(boolean isTop);
	
	public List<Event> findByNoTopAndStartTimeOrderByStartTimeDesc(Date startTime);
	
	public void deleteById(long id);
	
}
