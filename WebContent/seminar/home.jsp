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
		<title>ANZ線上投資論壇</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/css_reset.css" />" />
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/layout.css" />" />
		<script type="text/javascript" src="<c:url value="/js/jquery-1.6.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.tabs.pack.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/vallenato.js" />"></script>
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
			</div>
			<div id="content" class="clearfix">
				<div class="main home">
					<div class="title_bar">
						<h2>澳盛銀行線上論壇</h2>
						<p>本行將定期更新投資論壇影片，由財經專家深入解析當前財經趨勢及投資策略，讓您立即掌握投資先機。</p>
					</div>
					<c:if test="${headlineEvent != null}">
						<div class="hot_box clearfix">
							<div class="photo">
								<a href="${headlineEvent.eventUrl}"></a>
								<img src="<c:url value="/upload/images/${headlineEvent.uploadImage}" />"/>
							</div>
							<div class="text">
								<h3>${headlineEvent.summary}</h3>
								<p>${headlineEvent.introduction}</p>
								<a class="btn" href="${headlineEvent.eventUrl}">請立即觀看</a>
							</div>
						</div>
					</c:if>
					<div class="tab_box">
						<h2>理財焦點</h2>
						<div id="tab" class="tab">
							<ul>
								<li><a href="#fragment-1"><span>熱門新聞</span></a></li>
								<li><a href="#fragment-2"><span>每週理財快訊</span></a></li>
								<li><a href="#fragment-3"><span>每月投資市場觀測</span></a></li>
								<li>
									<a href="#fragment-4" onclick="window.open('https://hk.morningstar.com/pwd/hk/RBSAsia/QuickRank.aspx?CMarketId=MTW&CLang=CH?subsid=224')">
										<span>基金績效觀測站</span>
									</a>
								</li>
								<li class="line_none">
									<a href="#fragment-5" onclick="window.open('http://anz.tw/zh/personal/investment/index.jsp')">
										<span>更多投資產品</span>
									</a>
								</li>
							</ul>
							<div id="fragment-1">
								<iframe scrolling="no" frameborder="0" src="http://anz.tw/zh/member/member_rss_ib.jsp" style="width:100%; height:564px;"></iframe>
							</div>
							<div id="fragment-2">
								<iframe scrolling="auto" frameborder="0" src="http://anz.tw/zh/event/anz_review/ANZ_Weekly.pdf" style="width:100%; height:800px;"></iframe>
							</div>
							<div id="fragment-3">
								<iframe scrolling="auto" frameborder="0" src="http://anz.tw/zh/member/member_monthly_news_ib.jsp" style="width:100%; height:480px;"></iframe>
							</div>
							<div id="fragment-4"></div>
							<div id="fragment-5"></div>
						</div>
					</div>
					<div class="calendar">
						<div id="accordion-container">
							<h2 class="accordion-header clearfix">
								<span>講座行事曆</span>
								<span class="icon"></span>
							</h2>
							<div class="accordion-content ">
								<div class="calendar_box">
									<iframe src="<c:url value="https://www.google.com/calendar/embed?showTitle=0&showNav=1&showPrint=0&showCalendars=0&showTz=0&height=450&wkst=1&src=${account}&ctz=Asia%2FTaipei" />" style=" border-width:0 " width="650" height="450" frameborder="0" scrolling="no"></iframe>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="sidebar">
					<h2>近期講座</h2>
					<ul>
						<c:if test="${not empty eventList}">
							<c:forEach var="event" items="${eventList}">
								<li>
									<div class="photo">
										<a href="${event.eventUrl}"></a>
										<img src="<c:url value="/upload/images/${event.uploadImage}" />" />
									</div>
									<h4>${event.summary}</h4>
									<p class="name">${event.speaker}</p>
									<p class="time clearfix">
										<span class="time_left">
											<fmt:formatDate value="${event.startTime}" pattern="yyyy年MM月dd日 ah:mm"/>
										</span>
										<c:if test="${event.duration != null}">
											<span class="time_right">約${event.duration}分</span>
										</c:if>
									</p>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
			</div>
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
				$('#tab').tabs({ fxSlide: true, fxFade: true, fxSpeed: 'normal' });
			});
		</script>
	</body>
</html>