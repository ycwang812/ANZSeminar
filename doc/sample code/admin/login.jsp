<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="_include/util/common.jsp" %>
<html>

<head>
<meta http-equiv="Content-Language" content="zh-tw">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ANZABN．AMRO Bank [Taiwan] - Web Content Management System</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0">
<form name="form1" method="post" action="login_process.jsp">
<table border="0" width="955" height="600" align="center">
	<tr>
		<td align="center" valign="middle">
			<table border="0" width="500">
				<tr>
					<td>
						<table border="0" width="100%" class="table1" bgcolor="#FFFFFF" style="border-collapse: collapse">
							<tr>
								<td colspan="2" bgcolor="#696564"><img border="0" src="images/abnamro.jpg" width="119" height="55"></td>
							</tr>
							<tr class="header">
								<td colspan="2" align="center">　Welcome To Web Content Management System</td>
							</tr>
							<tr>
								<td align="right">User ID: </td>
								<td>
									<input type="text" name="userid" value="" size="30" class="text">
									<span id="check_account" style="color: #FF0000;"></span>
								</td>
							</tr>
							<tr>
								<td align="right">Password: </td>
								<td>
									<input type="password" name="password" value="" size="30" class="text">
									<span id="check_password" style="color: #FF0000;"></span>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2"><div class="sysmsg"><%= getSystemMessage(session) %></div></td>
							</tr>
							<tr>
								<td align="center" colspan="2" height="30">
									<input type="button" value=" Login " name="OK" class="btn" onclick="document.form1.submit();">
									<input type="reset" value="Reset" name="Reset" class="btn">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>

</html>