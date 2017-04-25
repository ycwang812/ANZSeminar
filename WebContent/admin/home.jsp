<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Cache-Control" content="no-cache">
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Expires" content="0">
		<title>Seminar Admin Home</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.css" />" />
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/layout.css" />" />
		<script type="text/javascript" src="<c:url value="/js/jquery-1.9.1.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/bootstrap.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/pagination.js" />"></script>
		<style type="text/css">
			iframe {
				padding-top: 20px;
			}
			
			table {
				font-size: 14px;
			}
		</style>
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
					<li class="line_none">
						<a href="<c:url value="/admin/adminLogoutAction.do" />">登出</a>
					</li>
				</ul>
			</div>
			<div class="container">
				<form action="" method="post" id="form">
					<input type="hidden" id="id" name="id" />
					<input type="hidden" id="eventId" name="eventId" />
				</form>
				<div>
					<button class="btn btn-info" onclick="addEvent();">New</button>
					&nbsp;&nbsp;
					<button class="btn btn-warning" id="clearBtn" onclick="clearTop();">Clear all top setup</button>
				</div>
				<br>
				<table border="0" cellpadding="5" cellspacing="1">
					<thead>
						<tr>
							<th>Seminar name</th>
							<th>Type</th>
							<th>Function</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty eventList}">
							<c:forEach var="event" items="${eventList}">
								<tr>
									<td>${event.summary}</td>
									<td>
										<c:if test="${event.seminarKind eq 'hangout'}">Live</c:if>
										<c:if test="${event.seminarKind eq 'record'}">Video</c:if>
									</td>
									<td>
										<a href="#" onclick="updateEvent('${event.id}')">Revise</a>&nbsp;&nbsp;/&nbsp;
										<a href="#" onclick="deleteEvent('${event.id}', '${event.eventId}')">Delete</a>&nbsp;&nbsp;/&nbsp;
										<a href="#" onclick="queryEvent('${event.id}')">Details</a>
										<c:if test="${event.question eq 'Y'}">
											&nbsp;/&nbsp;&nbsp;<a href="#" onclick="queryQuestion('${event.id}')">Question list</a>
										</c:if>
										<c:if test="${not empty event.youtubeId}">
											&nbsp;/&nbsp;&nbsp;<a href="#" onclick="queryReport('${event.id}')">Report</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<div class="calendar">
					<div class="calendar_box">
						<iframe src="<c:url value="https://www.google.com/calendar/embed?showTitle=0&showNav=1&showPrint=0&showCalendars=0&showTz=0&height=450&wkst=1&src=${account}&ctz=Asia%2FTaipei" />" style=" border-width:0 " width="650" height="450" frameborder="0" scrolling="no"></iframe>
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
			function addEvent() {
				location.href = "<c:url value="/admin/addAction.do" />";
			}
			
			function clearTop() {
				$("#clearBtn").hide();
				
				$.ajax({
				    url: "<c:url value="/admin/clearTopAction.do" />",
				    type: "POST",
				    success: function(data) {
				    	if (data == "success") {
							alert("Clear all top success！");
						}
				    	
				    	$("#clearBtn").show();
				    }
				});
			}
			
			function updateEvent(id) {
				$("#id").val(id);
				$("#form").attr("action", "<c:url value="/admin/updateAction.do" />");
				$("#form").submit();
			}
			
			function deleteEvent(id, eventId) {
				if (confirm("確定刪除？")) {
					$("#id").val(id);
					$("#eventId").val(eventId);
					$("#form").attr("action", "<c:url value="/admin/deleteEventAction.do" />");
					$("#form").submit();
				}
			}
			
			function queryEvent(id) {
				$("#id").val(id);
				$("#form").attr("action", "<c:url value="/admin/queryEventAction.do" />");
				$("#form").submit();
			}
			
			function queryQuestion(id) {
				$("#id").val(id);
				$("#form").attr("action", "<c:url value="/admin/queryQuestionAction.do" />");
				$("#form").submit();
			}
			
			function queryReport(id) {
				$("#id").val(id);
				$("#form").attr("action", "<c:url value="/admin/queryReportAction.do" />");
				$("#form").submit();
			}
		</script>
	</body>
</html>