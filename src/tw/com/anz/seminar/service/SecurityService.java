package tw.com.anz.seminar.service;

public interface SecurityService {

	public String checkAdminUser(String userId, String password);
	
	public String checkUser(String userId, String birthday);
	
}
