package tw.com.anz.seminar.service;

import java.util.Map;

import org.apache.log4j.Logger;

import tw.com.anz.seminar.dao.MemberDao;
import tw.com.anz.seminar.dao.UserAccountDao;

public class SecurityServiceImpl implements SecurityService {
	
	private Logger log = Logger.getLogger(getClass());

	private MemberDao memberDao;
	private UserAccountDao userAccountDao;
	
	public String checkAdminUser(String userId, String password) {
		Map<String, Object> map = userAccountDao.findByUserId(userId, password);
		
		if (map == null) {
			return null;
		}
		
		return (String) map.get("user_name");
	}
	
	public String checkUser(String userId, String birthday) {
		String[] s = birthday.split("/");
		
		birthday = s[0] + "-" + s[1] + "-" + s[2];
		
		// ANZ
//		return memberDao.getCif(userId, birthday);
		
		Map<String, Object> map = memberDao.findByUserId(userId, birthday);
		
		if (map == null) {
			return null;
		}
		
		return String.valueOf(map.get("Member_no"));
	}
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void setUserAccountDao(UserAccountDao userAccountDao) {
		this.userAccountDao = userAccountDao;
	}
	
}
