<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Cache-Control" content="no-cache">
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Expires" content="0">
		<title>Question List</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.css" />" />
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/layout.css" />" />
		<script type="text/javascript" src="<c:url value="/js/jquery-1.9.1.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/bootstrap.js" />"></script>
		<script type="text/javascript">
			var _gaq = _gaq || [];
			_gaq.push(['_setAccount', 'UA-19701560-1']);
			_gaq.push(['_trackPageview']);
	
			(function() {
				var ga = document.createElement('script');
				ga.type = 'text/javascript';
				ga.async = true;
				ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';

				var s = document.getElementsByTagName('script')[0];
				s.parentNode.insertBefore(ga, s);
			})();
		</script>
	</head>
	<body>
		<div id="wrapper">
			<div id="header" class="clearfix">
				<h1 class="logo text_none">
					<a href="http://anz.tw/zh/index.jsp">ANZ澳盛銀行</a>
				</h1>
				<ul class="nav clearfix">
					<li>
						<a href="javascript:back();">返回行事曆</a>
					</li>
					<li class="line_none">
						<a href="<c:url value="/admin/adminLogoutAction.do" />">登出</a>
					</li>
				</ul>
			</div>
			<div class="container">
				<form action="" method="post" id="form">
					<input type="hidden" id="id" name="id" value="${event.id}" />
				</form>
				<h3>${event.summary}</h3>
				<table class="table">
					<thead>
						<tr>
							<th width="20%">Time</th>
							<th width="20%">Asker</th>
							<th width="60%">Question</th>
						</tr>
					</thead>
					<tbody id ="tbody">
						<c:if test="${not empty questionList}">
							<c:forEach var="question" items="${questionList}">
								<tr>
									<td><fmt:formatDate value="${question.time}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
									<td>${question.userId}</td>
									<td>${question.question}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<div class="control-group">
					<div class="controls">
						<button class="btn btn-info" onclick="download();">Download question</button>
						&nbsp;&nbsp;
						<button class="btn btn-success" onclick="back();">Back</button>
					</div>
				</div>
			</div>
			<br>
			<div id="footer" class="clearfix">
				<div class="fl_box">
					<ul class="nav clearfix">
						<li class="pl_0"><a href="http://anz.tw/zh/security/security-1.jsp">網路安全</a></li>
						<li><a href="http://anz.tw/zh/terms/privacy.jsp">個人隱私保護</a></li>
						<li><a href="http://anz.tw/zh/terms/use-of-terms.jsp">銀行責任歸屬</a></li>
						<li><a href="http://anz.tw/zh/about-us/careers.jsp">加入澳盛</a></li>
						<li class="line_none"><a href="http://anz.tw/zh/sitemap/sitemap.jsp">網站地圖</a></li>
					</ul>
					<p class="fs11">&copy;澳盛(台灣)商業銀行股份有限公司</p>
				</div>
				<div class="fr_box">
					<div class="logo text_none">
						<a href="http://anz.tw/zh/index.jsp">ANZ澳盛銀行</a>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(function() {
				setInterval(reload, 10000);
			});
			
			function reload() {
				$.ajax({
				    url: "<c:url value="/admin/reloadQuestionAction.do" />",
				    data: { id: $("#id").val() },
				    type: "POST",
				    dataType: "json",
				    success: function(data) {
				    	if (data != "") {
				    		$("#tbody").empty();
				    		
				    		for (var i = 0; i < data.length; i++) {
				    			$("#tbody").append(
				    				"<tr><td>" + data[i].time + "</td><td>" + data[i].userId + "</td><td>" + data[i].question +"</td></tr>"
				    			);
				    		}
						}
				    }
				});
			}
			
			function download() {
				$("#form").attr("action", "<c:url value="/admin/downlaodQuestionAction.do" />");
				$("#form").submit();
			}
		
			function back() {
				$("#form").attr("action", "<c:url value="/admin/adminHomeAction.do" />");
				$("#form").submit();
			}
		</script>
	</body>
</html>