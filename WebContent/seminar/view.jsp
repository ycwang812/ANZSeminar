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
		<script type="text/javascript" src="<c:url value="/js/jquery.reveal.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/javascript.js" />"></script>
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
					<c:choose>
						<c:when test="${userId eq 'Guest'}">
							<li class="line_none">
								<a href="<c:url value="/seminar/homeAction.do" />">返回行事曆</a>
							</li>
						</c:when>
						<c:otherwise>
							<li>
								<a href="<c:url value="/seminar/homeAction.do" />">返回行事曆</a>
							</li>
							<li class="line_none">
								<a href="<c:url value="/seminar/homeAction.do" />">登出</a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<div id="content" class="clearfix">
				<div class="main">
					<div class="title_bar">
						<h2>${event.summary}</h2>
						<p id="time" class="time">
							<fmt:formatDate value="${event.startTime}" pattern="yyyy年MM月dd日 ah:mm"/>
						</p>
						<p id="time2" class="time2">
							講座開始時間： 
							<fmt:formatDate value="${event.startTime}" pattern="yyyy年MM月dd日 ah:mm"/>
						</p>
						<c:if test="${event.uploadFile != null}">
							<a href="<c:url value="/upload/files/${event.uploadFile}" />" class="btn">
								講座摘要
								<em></em>
							</a>
						</c:if>
					</div>
					<div class="movie">
						<div id="wait">
							<img src="<c:url value="/images/wait.png" />">
						</div>
						<div id="player"></div>
					</div>
					<div id="hr" class="hr"></div>
					<div id="problem" class="problem">
						<p>因時間有限，若無法線上即時回答您所提出的投資理財相關問題，將有專人與您聯繫。</p>
						<div class="accordionButton">
							<a href="javascript:void(0)" class="btn">提出問題</a>
						</div>
						<div class="accordionContent">
							<form action="" method="post" id="form">
								<input type="hidden" id="id" name="id" value="${event.id}">
        						<input type="hidden" id="userId" name="userId" value="${userId}">
								<div class="textarea">
									<textarea id="question" class="w530 h50"></textarea>
								</div>
								<a href="javascript:void(0)" class="btn" data-reveal-id="myModal" data-animation="none" onclick="save();">送出</a>
								<a href="javascript:void(0)" class="close">關閉視窗</a>
							</form>
						</div>
					</div>
					<div class="note">
						<h3>注意事項：</h3>
						<ol>
							<li>此影音檔版權為澳盛銀行所有，非經本行同意，嚴禁轉載、錄影、複製或引用，若有違反，本行將保留法律追訴權。</li>
							<li>本活動因故無法進行時，本行有權隨時終止、取消、修改或暫停本活動。</li>
						</ol>
						<h3>免責聲明：</h3>
						<p>${event.legal}</p>
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
			var youtubeId = "${event.youtubeId}";
			var question = "${event.question}";
			var openTime = "${openTime}";
			
			$(function() {
				if (youtubeId != "") {
					$("#time2").hide();
					$("#wait").hide();
					startPlayer();
				} else {
					$("#time").hide();
					setTimeout(getYouTubeId, 10000);
				}
				
				if (question == "Y") {
					$("#hr").hide();
				} else {
					$("#problem").hide();
				}
				
				$(".close").live('click', function() {
					$(".accordionButton").removeClass("on");
					$(".accordionContent").hide();
				});
			});
			
			function save() {
				if ($("#question").val() == "") {
					alert("請填寫問題！");
					$("#question").focus();
					return;
				}
				
				$.ajax({
				    url: "<c:url value="/seminar/saveQuestionAction.do" />",
				    data: { id: $("#id").val(), userId:$("#userId").val(), question:$("#question").val() },
				    type: "POST",
				    success: function(data) {
				    	if (data == "success") {
							alert("問題已送出，感謝您的提問。");
							$(".accordionButton").removeClass("on");
							$(".accordionContent").hide();
							$("#question").val("");
						}
				    }
				});
			}
			
			function getYouTubeId() {
				$.ajax({
				    url: "<c:url value="/seminar/queryYouTubeAction.do" />",
				    data: { id: $("#id").val() },
				    type: "POST",
				    success: function(data) {
				    	if (data != "") {
							youtubeId = data;
							
							$("#time").show();
							$("#time2").hide();
							$("#wait").hide();
							startPlayer();
						} else {
							setTimeout(getYouTubeId, 10000);
						}
				    }
				});
			}
			
			var tag;
			var firstScriptTag;
			var player;
			var interval;
			
			function startPlayer() {
				tag = document.createElement('script');
				tag.src = "https://www.youtube.com/iframe_api";
				firstScriptTag = document.getElementsByTagName('script')[0];
				firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
			}
			
			function onYouTubeIframeAPIReady() {
				player = new YT.Player('player', {
					height : '390',
					width : '640',
					videoId : youtubeId,
					playerVars: { 
						'showinfo': 0, 
						'showsearch': 0,
						'rel' : 0,
						'controls' : 1
					},
					events : {
						//'onReady' : onPlayerReady,
						'onError' : onPlayerError,
						'onStateChange' : onPlayerStateChange
					}
				});
				
				interval = setInterval(updatePlayerInfo, 10000);
				updatePlayerInfo();
			}
			
			function onPlayerReady(event) {
				event.target.playVideo();
			}
			
			function onPlayerError(event) {
				if (event.data == 0) {
					setTimeout(reload, 10000);
				}
			}
			
			function onPlayerStateChange(event) {
				if (event.data == YT.PlayerState.ENDED) {
					 clearInterval(interval);
					 updatePlayerInfo();
				}
			}
			
			function reload() {
				player.loadVideoById({'videoId' : youtubeId});
			}
			
			function updatePlayerInfo() {
				if (player && player.getDuration) {
					$.ajax({
					    url: "<c:url value="/seminar/reportAction.do" />",
					    data: { id: $("#id").val(), userId:$("#userId").val(), openTime:openTime, watchTime:player.getCurrentTime() },
					    type: "POST",
					});
				}
			}
		</script>
	</body>
</html>