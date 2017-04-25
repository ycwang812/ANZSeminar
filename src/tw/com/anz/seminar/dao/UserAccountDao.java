package tw.com.anz.seminar.dao;

import java.util.Map;

public interface UserAccountDao {

	public Map<String, Object> findByUserId(String userId, String password);
	
}
