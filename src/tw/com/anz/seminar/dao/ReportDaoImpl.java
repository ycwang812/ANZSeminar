package tw.com.anz.seminar.dao;

import java.util.Date;
import java.util.List;

import tw.com.anz.seminar.model.Report;

public class ReportDaoImpl extends AbstractBaseDao implements ReportDao {

	@Override
	protected Class<Report> getEntityClass() {
		return Report.class;
	}
	
	public long getWatchTime(long id) {
		Report report = (Report) findById(id);
		evict(report);
		return report != null ? report.getWatchTime() : -1;
	}
	
	public long countViews(long eId) {
		String sql = "select count(id) from Report where eId = ?";
		Object[] param = { eId };
		return (Long) find(sql, param).listIterator().next();
	}
	
	public long countViews(long eId, Date stratDate, Date endDate) {
		String sql = "select count(id) from Report where eId = ? and openTime >= ? and openTime <= ?";
		Object[] param = { eId, stratDate,endDate  };
		return (Long) find(sql, param).listIterator().next();
	}
	
	public long sumWatchTime(long eId) {
		String sql = "select sum(watchTime) from Report where eId = ?";
		Object[] param = { eId };
		return (Long) find(sql, param).listIterator().next();
	}
	
	public long sumWatchTime(long eId, Date stratDate, Date endDate) {
		String sql = "select sum(watchTime) from Report where eId = ? and openTime >= ? and openTime <= ?";
		Object[] param = { eId, stratDate, endDate };
		return (Long) find(sql, param).listIterator().next();
	}
	
	public List<Report> findByEIdOrderByOpenTime(long eId) {
		String sql = "from Report where eId = ? order by openTime desc";
		Object[] param = { eId };
		return find(sql, param);
	}
	
	public List<Report> findByEId(long eId) {
		String sql = "from Report where eId = ?";
		Object[] param = { eId };
		return find(sql, param);
	}
	
	public void deleteByEId(long eId) {
		String sql = "Delete from Report where eId = ?";
		Object[] param = { eId };
		this.bulkUpdate(sql, param);
	}

}
