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
		<title>Details</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.css" />" />
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/layout.css" />" />
		<script type="text/javascript" src="<c:url value="/js/jquery-1.9.1.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/bootstrap.js" />"></script>
		<style type="text/css">
			table {
				font-size: 16px;
				line-height: 2;
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
					<li>
						<a href="javascript:back();">返回行事曆</a>
					</li>
					<li class="line_none">
						<a href="<c:url value="/admin/adminLogoutAction.do" />">登出</a>
					</li>
				</ul>
			</div>
			<div class="container">
				<form action="" method="post" id="form" class="form-horizontal">
					<input type="hidden" id="id" name="id" value="${event.id}" />	
				</form>	
				<h3>Seminar Detail</h3>
				<table class="table">
					<tr>
						<td width="25%">Seminar Name</td>
						<td>${event.summary}</td>
					</tr>
					<tr>
						<td>Seminar Date/Time</td>
					 <td>
					 	<fmt:formatDate value="${event.startTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
						&nbsp;till&nbsp;
						<fmt:formatDate value="${event.endTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
					 </td>
					</tr>
					<tr>
						<td>Introduction of seminar</td>
						<td>${event.introduction}</td>
					</tr>
					<tr>
						<td>Speaker</td>
						<td>${event.speaker}</td>
					</tr>
					<c:choose>
						<c:when test="${event.seminarKind eq 'hangout'}">
							<tr>
								<td>Type</td>
				 				<td>Live</td>
							</tr>
							<tr>
								<td>YouTube URL</td>
					 			<td>${event.youtubeUrl}</td>
							</tr>
							<tr>
								<td>Privacy</td>
					 			<td>
					 				<c:if test="${event.privacyStatus eq 'unlisted'}">Unlisted</c:if>
									<c:if test="${event.privacyStatus eq 'public'}">Public</c:if>
									<c:if test="${event.privacyStatus eq 'private'}">Private</c:if>
				 				</td>
							</tr>
							<tr>
								<td>Raise question via on-line</td>
					 			<td>
					 				<c:if test="${event.question eq 'Y'}">Yes</c:if>
									<c:if test="${event.question eq 'N'}">No</c:if>
				 				</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td>Type</td>
				 				<td>Video</td>
							</tr>
							<tr>
								<td>Video URL</td>
					 			<td>${event.url}</td>
							</tr>
						</c:otherwise>
 					</c:choose>
					<tr>
						<td>Attachment</td>
						<td>${event.uploadImage}</td>
					</tr>
					<tr>
						<td>Legal terms</td>
						<td>${event.legal}</td>
					</tr>
					<tr>
						<td>Seminar summary</td>
						<td>${event.uploadFile}</td>
					</tr>
					<tr>
						<td>Duration</td>
						<td>${event.duration}</td>
					</tr>
					<tr>
						<td>Reset layout</td>
						<td>
							<c:if test="${event.headline eq 'Y'}">
								<div>Headline</div>
							</c:if>
							<c:if test="${event.top eq 'Y'}">
								<div>Top</div>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>Log-in Setting</td>
						<td>
							<c:if test="${event.login eq 'Y'}">Log-in</c:if>
							<c:if test="${event.login eq 'N'}">Non log-in</c:if>
						</td>
					</tr>
					<tr>
						<td>Seminar URL</td>
						<td>
							<a href="${event.eventUrl}" target="_blank">${event.eventUrl}</a>
						</td>
					</tr>
					<tr>
						<td>Calendar URL</td>
						<td>
							<a href="${icsUrl}">${icsUrl}</a>
						</td>
					</tr>
				</table>
				<div class="control-group">
					<div class="controls">
						<button class="btn btn-info" onclick="updateEvent();">Revise</button>
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
			function updateEvent() {
				$("#form").attr("action", "<c:url value="/admin/updateAction.do" />");
				$("#form").submit();
			}
		
			function back() {
				$("#form").attr("action", "<c:url value="/admin/adminHomeAction.do" />");
				$("#form").submit();
			}
		</script>
	</body>
</html>