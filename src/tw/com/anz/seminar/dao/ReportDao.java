package tw.com.anz.seminar.dao;

import java.util.Date;
import java.util.List;

import tw.com.anz.seminar.model.Report;

public interface ReportDao extends BaseDao {
	
	public long getWatchTime(long id);
	
	public long countViews(long eId);
	
	public long countViews(long eId, Date stratDate, Date endDate);
	
	public long sumWatchTime(long eId);
	
	public long sumWatchTime(long eId, Date stratDate, Date endDate) ;
	
	public List<Report> findByEIdOrderByOpenTime(long eId);
	
	public List<Report> findByEId(long eId);
	
	public void deleteByEId(long eId);

}
