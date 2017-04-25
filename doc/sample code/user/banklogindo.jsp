<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%> 
<%@page import="java.sql.*"%>
<%@include file="common.jsp"%>
<%@ page import="tw.com.adcast.util.Crypto, java.text.SimpleDateFormat, java.util.Date"%>
<%@include file="user.jsp"%>
<%

        Connection conn = tw.com.adcast.util.BaseDAO.getDataSource().getConnection();
        PreparedStatement ps = null;
	ResultSet rs = null;
	StringBuffer sql = null;

        PreparedStatement ps1 = null;
	ResultSet rs1 = null;
	StringBuffer sql1 = null;

        sql = new StringBuffer();
	sql.append(" select * from MemberParam where pmid=1");
	ps = conn.prepareStatement(sql.toString());
	rs = ps.executeQuery();
	rs.next();
        int stimeout = getInt(rs, "stimeout", 1800);
			
        String bidno = getString(request, "bidno", "");
        String bbrith = getString(request, "bbrith", "");
        int c_MemberNo = getInt(request, "c_MemberNo", 0);

	if (bidno.equals("")) {
                out.print("<script language=javascript>alert('請輸入身分證字號！');window.location.href='" + WEBROOT + "member/member_index.jsp';</script>");
		return;
	}

	if (bbrith.equals("")) {
                out.print("<script language=javascript>alert('請輸入生日！');window.location.href='" + WEBROOT + "member/member_index.jsp';</script>");
		return;
	}else{
		if(bbrith.length()!=8)
		{
			out.print("<script language=javascript>alert('生日格式錯誤，請輸入YYYYMMDD！');window.location.href='" + WEBROOT + "member/member_index.jsp';</script>");
	  		return;
		}
		
		String ybrith = bbrith.substring(0,4);
		if(Integer.parseInt(ybrith)>9999|Integer.parseInt(ybrith)<1000)
		{
			out.print("<script language=javascript>alert('生日格式年份錯誤，請重新輸入！');window.location.href='" + WEBROOT + "member/member_index.jsp';</script>");
	  		return;
		}
		
		String mbrith = bbrith.substring(4,6);
		if(Integer.parseInt(mbrith)>12|Integer.parseInt(mbrith)<1)
		{
			out.print("<script language=javascript>alert('生日格式月份錯誤，請重新輸入！');window.location.href='" + WEBROOT + "member/member_index.jsp';</script>");
	  		return;
		}
		
		String dbrith = bbrith.substring(6,8);
		if(Integer.parseInt(dbrith)>31|Integer.parseInt(dbrith)<1)
		{
			out.print("<script language=javascript>alert('生日日期格式錯誤，請重新輸入！');window.location.href='" + WEBROOT + "member/member_index.jsp';</script>");
	  		return;
		}
		
	}

	

        bbrith = bbrith.substring(0,4) + "-" +  bbrith.substring(4,6) + "-" + bbrith.substring(6,8);
        sql = new StringBuffer();
        //要改
    //sql.append("exec [twas-ccc38].[crmtwdb].dbo.getcif '" + bidno + "', '" + bbrith + "' ") ;
    sql.append("exec [SGASC02169\\TWCRM].[crmtwdb].dbo.getcif '" + bidno + "', '" + bbrith + "' ") ;
	//sql.append(" select * from bandmember where ID ='" + bidno + "' and Birth='" + bbrith + "' " );
        ps = conn.prepareStatement(sql.toString());
	rs = ps.executeQuery();
        if(!rs.next())
	{
	  out.print("<script language=javascript>alert('身分證字號或生日錯誤，請重新輸入！');window.location.href='" + WEBROOT + "member/member_index.jsp';</script>");
	  return;
	}
	else
	{

                Crypto crypto = getCrypto(session, conn);
                java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
		long now = calendar.getTimeInMillis();        
	        
	                        
                String Name = getString(rs, "Name", "");
                String Gender = getString(rs, "Gender", "");
                String GType = getString(rs, "Type", "");
                String Gifno = getString(rs, "CIF No", ""); 
                String AoCode = getString(rs, "AO Code", ""); 
                String Branch = getString(rs, "Branch", ""); //換商品

	         //找是否存在會員表格內
		sql1 = new StringBuffer();
		sql1.append(" select * from Member where idno ='" + bidno + "' and birth='" + bbrith + "' " );
		ps1 = conn.prepareStatement(sql1.toString());
		rs1 = ps1.executeQuery();
	        if(!rs1.next())
		{
		  //不存在
                 String addrow[] = new String[9]; //新增欄位有幾個
	         String tmpwhy = ""; 
	         addrow[0] ="Gpid";
	         addrow[1] ="Guest_sex";
	         addrow[2] ="applydate";
	         addrow[3] ="logcount";
	         addrow[4] ="Guest_status";
	         addrow[5] ="errlogcount";
	         addrow[6] ="idno";
	         addrow[7] ="birth";
	         addrow[8] ="Guest_name";
	        

	                String insertstr = "insert into Member(";
			for(int a=0;a<addrow.length ;a++) 
			{	
				insertstr = insertstr + addrow[a] + ",";
				tmpwhy = tmpwhy + "?,";
			}	
	        
			insertstr = insertstr.substring(0,insertstr.length()-1) + ") values(" + tmpwhy.substring(0,tmpwhy.length()-1) + ")";
			PreparedStatement pstmt=conn.prepareStatement(insertstr); 
			String tmpGpid = "";
			
                        if(GType.equals("GB"))
			{tmpGpid = "03";}
			if(GType.equals("EB"))
			{tmpGpid = "04";}
			if(GType.equals("PB"))
			{tmpGpid = "05";}

	                pstmt.setString(1,tmpGpid);
			pstmt.setString(2,Gender);
			pstmt.setTimestamp(3, new java.sql.Timestamp(now));
			pstmt.setInt(4,0);
			pstmt.setString(5,"1");
			pstmt.setInt(6,0);
			pstmt.setString(7,bidno);
			pstmt.setString(8,bbrith);
			pstmt.setBytes(9, crypto.encrypt(Name));
		        pstmt.executeUpdate(); 
		        pstmt.close();
		}

                //找會員
		sql1 = new StringBuffer();
		sql1.append(" select * from Member where idno ='" + bidno + "' and birth='" + bbrith + "' " );
		ps1 = conn.prepareStatement(sql1.toString());
		rs1 = ps1.executeQuery();
	        rs1.next();

                 int m_id = getInt(rs1, "Member_no", 0);
                 String userid = String.valueOf(m_id);
                 String Gpid = getString(rs1, "Gpid", "");
                 String Guest_status = getString(rs1, "Guest_status", "");
                 String lastlogdate = getString(rs1, "lastlogdate", ""); //上一次的登入時間
                 int logcount = getInt(rs1, "logcount", 0);
                 String Guest_sex = getString(rs1, "Guest_sex", "");                 

                 if (Guest_status.equals("2"))
                 {
                   out.print("<script language=javascript>alert('您的帳號已被停用，請洽客服人員！');window.location.href='" + WEBROOT + "member/member_index.jsp';</script>");
                   return;
                 }
                //====================
	        if(isLogined(userid))
		{
                    out.print("<script language=javascript>alert('此帳號已經登入了！');window.location.href='" + WEBROOT + "member/member_index.jsp';</script>");
                    return;
		}   
		else
	        {
	                tw.com.adcast.bean.User user = new tw.com.adcast.bean.User();
			user.set("userid", userid);
	                session.setMaxInactiveInterval(stimeout);
			login(session, user);
	         }
                
                 //====================
                       logcount = logcount + 1;
   	                String addrow1[] = new String[5]; //修改欄位有幾個
		        String tmpvalue1 = "";
	                addrow1[0] ="lastlogdate";
	                addrow1[1] ="errlogcount";
	                addrow1[2] ="logcount";
	                addrow1[3] ="Guest_name";
	                addrow1[4] ="campaignMemberNo";
			for(int a=0;a<addrow1.length ;a++) 
			{	
                           tmpvalue1 = tmpvalue1 + addrow1[a] + "=?,";
			}	
			String updatestr1="update Member set " + tmpvalue1.substring(0,tmpvalue1.length()-1) + " where Member_no=" + m_id;
			PreparedStatement pstmt1=conn.prepareStatement(updatestr1); 
			pstmt1.setTimestamp(1, new java.sql.Timestamp(now));
			pstmt1.setInt(2,0);
			pstmt1.setInt(3,logcount);
			pstmt1.setBytes(4, crypto.encrypt(Name));
			pstmt1.setInt(5,c_MemberNo);
		        pstmt1.executeUpdate(); 
		        pstmt1.close();
                   //===========================

                   session.setMaxInactiveInterval(stimeout);
	           session.putValue("Member_no",m_id);
	           session.putValue("Gpid",Gpid);
	           session.putValue("Guest_name",Name);
		   session.putValue("Guest_email","");
		   session.putValue("Guest_mobile","");
		   session.putValue("lastlogdate",lastlogdate);	
		   session.putValue("Guest_sex",Guest_sex);				
		   session.putValue("Pidno",bidno);   //信用卡及銀行才有的身分證
		   session.putValue("Gifno",Gifno);
		   session.putValue("AoCode",AoCode);
		   session.putValue("Branch",Branch);



		   //++++++++++++++++++++++++++++++++++
		   //20081208
		   //Nick Lin
		   //新增一個 session 變數用以識別是否由此登入 
		   session.putValue("LoginChannel","bank");
		   //++++++++++++++++++++++++++++++++++

            out.print("<script language=javascript>alert('登入成功！');window.location.href='" + WEBROOT + "member/member_rss.jsp?subsid=200';</script>");	    
	          //out.print("<script language=javascript>window.location.href='" + WEBROOT + "member/mgm_explain.jsp?subsid=109&subuplink=215';</script>");
	          //out.print("<script language=javascript>window.location.href='" + WEBROOT + "member/member_weekly_news.jsp?subsid=105';</script>");	
	           //out.print("<script language=javascript>window.location.href='" + WEBROOT + "member/member_lifestyle.jsp?qstype=04&subsid=103&subuplink=&qsemno=1';</script>");	
	        if(c_MemberNo != 0 && !"undefine".equals(c_MemberNo)){
       			String c_Sql0="update CampaignMember set webSerialNo=?, webLastLoginTime=? where webSerialNo is null and webLastLoginTime is null and memberNo=" + c_MemberNo;
       			PreparedStatement c_pstmt=conn.prepareStatement(c_Sql0); 
       			c_pstmt.setInt(1, m_id);
       			c_pstmt.setTimestamp(2,new java.sql.Timestamp(now));
       			c_pstmt.executeUpdate(); 
       			c_pstmt.close();
       			
      			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      			Date lastLoginTime = new Date();
      			Calendar c = Calendar.getInstance();
      			c.setTime(lastLoginTime);
      			c.add(Calendar.DATE, -1);
      			String c_Sql3 = "update campaignmember set scorepk = scorepk + 30 * (select count(*) from campaignpklog where pktime < '"+format.format(lastLoginTime)+"' and pktime >'"+format.format(c.getTime())+"' and losermemberno = "+c_MemberNo+" and recover = 0) where memberno = "+c_MemberNo;
      			Statement c_stmt3=conn.createStatement();
      			c_stmt3.executeUpdate(c_Sql3);
      			c_stmt3.close();
      					        			
      			String c_Sql4 = "update CampaignPKLog set recover = 1 where pktime < '"+format.format(lastLoginTime)+"' and pktime >'"+format.format(c.getTime())+"' and losermemberno = "+c_MemberNo+" and recover = 0";
      			Statement c_stmt4=conn.createStatement();
      			c_stmt4.executeUpdate(c_Sql4);
      			c_stmt4.close();
       		}
	}
%>