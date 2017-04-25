package tw.com.anz.seminar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public class MemberDaoImpl implements MemberDao {
	
	private Logger log = Logger.getLogger(getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public String getCif(String userId, String birthday) {
		StringBuilder sql = new StringBuilder();
		// ANZ UAT
		//sql.append("exec [sgvm-02154].[crmtwdb].dbo.getcif '").append(userId).append("', '").append(birthday).append("'");
		// ANZ Production
		sql.append("exec [SGASC02169\\TWCRM].[crmtwdb].dbo.getcif '").append(userId).append("', '").append(birthday).append("'");
		
		log.debug("JDBC SQL: " + sql.toString());
		
		String cifNo = null;
		
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				cifNo = rs.getString("CIF No");
			}
		} catch (Exception ex) {
			log.error("getCif", ex);
			return null;
		}
		
		return cifNo;
	}
	
	public Map<String, Object> findByUserId(String userId, String birthday) {
		String sql = "select * from Member where idno = ? and birth = ?";
		
		Object[] param = { userId, birthday };
		
		log.debug("JDBC SQL: " + sql.toString());
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, param);
		
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
