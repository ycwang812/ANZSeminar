<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="tw.com.adcast.util.Crypto"%>
<%@ include file="_include/util/common.jsp" %>
<%@ include file="_include/util/user.jsp" %>
<%
	
	StringBuffer sql = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	String userid = getString(request, "userid", "");
	String password = getString(request, "password", "");
	
	String check = null;
	check = isValidValue("User ID", userid, true, 30);
	if (! check.equals("valid")) {
		setSystemMessage(session, check);
		response.sendRedirect("login.jsp");
		return;
	}
	
	check = isValidValue("Password", password, true, 30);
	if (! check.equals("valid")) {
		setSystemMessage(session, check);
		response.sendRedirect("login.jsp");
		return;
	}
	
	if (isLogined(userid)) {
		setSystemMessage(session, "This account has already logined.");
		response.sendRedirect("login.jsp");
		return;
	}
	
	Connection conn = getConnection();
	Crypto crypto = getCrypto(session, conn);
	
	sql = new StringBuffer();
	//sql.append(" select * from UserAccount inner join Role on UserAccount.role_id = Role.role_id where userid = ? and password = ? ");
	sql.append(" select * from UserAccount inner join Role on UserAccount.role_id = Role.role_id where userid = ?");
	ps = conn.prepareStatement(sql.toString());
	ps.setBytes(1, crypto.encrypt(userid));
	//ps.setBytes(2, crypto.encrypt(password));
	rs = ps.executeQuery();
	
	String account_id = null;
	String user_name = null;
	String user_email = null;
	String user_dept = null;
	String user_status = null;
	String is_confirmed = null;
	String is_locked = null;
	String role_id = null;
	String role_name = null;
	java.util.Date modify_date = null;
	String login_date = null;
	if (rs.next()) {
		account_id = getString(rs, "account_id", "0");
		user_name = getString(rs, "user_name", "");
		user_email = getString(rs, "user_email", "");
		user_dept = getString(rs, "user_dept", "");
		user_status = getString(rs, "user_status", "0");
		is_confirmed = getString(rs, "is_confirmed", "0");
		is_locked = getString(rs, "is_locked", "0");
		role_id = getString(rs, "role_id", "0");
		role_name = getString(rs, "role_name", "");
		modify_date = rs.getDate("modify_date");
		login_date = getString(rs, "login_date", "");
		
		rs.close();
		ps.close();
		
		java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
		long now = calendar.getTimeInMillis();
		
		sql = new StringBuffer();
		sql.append(" update UserAccount set login_date = ? where userid = ? ");
		ps = conn.prepareStatement(sql.toString());
		ps.setTimestamp(1, new java.sql.Timestamp(now));
		ps.setBytes(2, crypto.encrypt(userid));
		ps.executeUpdate();
		ps.close();
		
		if (user_status.equals("0")) {
			setSystemMessage(session, "This user is currently not available or banned by system administrator.");
			response.sendRedirect("login.jsp");
			return;
		}
		
		if (is_confirmed.equals("0")) {
			setSystemMessage(session, "This user is not confirmed by system administrator.");
			response.sendRedirect("login.jsp");
			return;
		}
		
		/*
		if (is_locked.equals("1")) {
			setSystemMessage(session, "This user is locked by system.");
			response.sendRedirect("login.jsp");
			return;
		}
		*/
		tw.com.adcast.bean.User user = new tw.com.adcast.bean.User();
		user.set("account_id", account_id);
		user.set("userid", userid);
		user.set("user_name", user_name);
		user.set("user_email", user_email);
		user.set("user_dept", user_dept);
		user.set("role_id", role_id);
		user.set("role_name", role_name);
		user.set("login_date", login_date.toString());
		
		if (login_date.equals("")) {
			session.setAttribute("user", user);
			response.sendRedirect("change_password.jsp");
			return;
		}
		
		if (modify_date != null) {
			long one_day = 1000 * 3600 * 24;
			if ((now - modify_date.getTime()) / one_day > 30) {
				session.setAttribute("user", user);
				response.sendRedirect("change_password.jsp");
				return;
			}
		}
		
		sql = new StringBuffer();
		sql.append(" insert into LoginLog (account_id, activity_type, activity_content) ");
		sql.append(" values (?, ?, ?) ");
		ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, Integer.parseInt(account_id));
		ps.setString(2, "Login");
		ps.setString(3, "Login Successful");
		ps.executeUpdate();
		ps.close();

		conn.close();
		
		login(session, user);
		
		response.sendRedirect("index.jsp");
		return;
	} else {
		Integer times = (Integer) session.getAttribute("login_failed_times");
		if (times == null) {
			times = new Integer(1);
		} else {
			times ++;
		}
		
		sql = new StringBuffer();
		sql.append(" insert into LoginLog (account_id, activity_type, activity_content) ");
		sql.append(" values (?, ?, ?) ");
		ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, 0);
		ps.setString(2, "Login");
		ps.setString(3, "User ID:" + userid + "\r\nLogin Failed");
		ps.executeUpdate();
		ps.close();
		
		if (times >= 3) {
			sql = new StringBuffer();
			sql.append(" update UserAccount set is_locked = '1' where userid = ? ");
			ps = conn.prepareStatement(sql.toString());
			ps.setBytes(1, crypto.encrypt(userid));
			ps.executeUpdate();
			ps.close();
			
			session.removeAttribute("login_failed_times");
			setSystemMessage(session, "This user id is denied by system.");
			
		} else {
			session.setAttribute("login_failed_times", times);
			setSystemMessage(session, "Invalid user id or password.");
			
		}

		conn.close();
		
		response.sendRedirect("login.jsp");
		return;
	}
	
%>