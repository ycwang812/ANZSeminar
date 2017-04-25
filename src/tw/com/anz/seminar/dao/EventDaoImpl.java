package tw.com.anz.seminar.dao;

import java.util.Date;
import java.util.List;

import tw.com.anz.seminar.Constant;
import tw.com.anz.seminar.model.Event;

public class EventDaoImpl extends AbstractBaseDao implements EventDao {

	@Override
	protected Class<Event> getEntityClass() {
		return Event.class;
	}
	
	public List<Event> getAllOrderByIdDesc() {
		String sql = "from Event order by id desc";
		return find(sql);
	}
	
	public Event findEventById(long id) {
		return (Event) findById(id);
	}

	public Event findByEventId(String eventId) {
		String sql = "from Event where eventId = ?";
		Object[] param = { eventId };
		List<Event> list = find(sql, param);
		return list != null ? list.get(0) : null;
	}
	
	public Event findByHeadline() {
		String sql = "from Event where headline = ?";
		Object[] param = { Constant.YES };
		List<Event> list = find(sql, param);
		return list != null ? list.get(0) : null;
	}
	
	public List<Event> getAllOrderByStartTimeDesc() {
		String sql = "from Event order by startTime desc";
		List<Event> list = find(sql);
		return list != null ? list : null;
	}
	
	public List<Event> findByTopOrderByStartTimeDesc(boolean isTop) {
		StringBuilder sql = new StringBuilder();
		sql.append("from Event where ");
		
		if (isTop) {
			sql.append("top is not null ");
		} else{
			sql.append("top is null ");
		}
		
		sql.append("order by startTime desc");
		
		List<Event> list = find(sql.toString());
		return list != null ? list : null;
	}
	
	public List<Event> findByNoTopAndStartTimeOrderByStartTimeDesc(Date startTime) {
		String sql = "from Event where top is null and startTime >= ? order by startTime desc";
		Object[] param = { startTime };
		List<Event> list = find(sql, param);
		return list != null ? list : null;
	}
	
	public void deleteById(long id) {
		String sql = "Delete from Event where id = ?";
		Object[] param = { id };
		this.bulkUpdate(sql, param);
	}
	
}
