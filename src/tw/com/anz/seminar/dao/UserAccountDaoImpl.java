package tw.com.anz.seminar.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserAccountDaoImpl implements UserAccountDao {
	
	private Logger log = Logger.getLogger(getClass());

	private JdbcTemplate jdbcTemplate;
	
	public Map<String, Object> findByUserId(String userId, String password) {
		String sql = "select * from UserAccount inner join Role on UserAccount.role_id = Role.role_id where userid = ? and password = ?";
		
		Object[] param = { userId, password };
		
		log.debug("JDBC SQL: " + sql.toString());
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, param);
		
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
}
