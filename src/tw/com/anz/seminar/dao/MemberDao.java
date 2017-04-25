package tw.com.anz.seminar.dao;

import java.util.Map;

public interface MemberDao {
	
	public String getCif(String userId, String birthday);
	
	public Map<String, Object> findByUserId(String userId, String birthday);

}
