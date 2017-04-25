<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
		<script type="text/javascript" src="<c:url value="/js/jquery-1.9.1.min.js" />"></script>
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
			<div id="content">
				<div class="login_box">
					<div class="login">
						<form action="<c:url value="/seminar/loginAction.do" />" method="post" id="form">
							<input type="hidden" id="v" name="v" value="${v}" />
							<h2>澳盛銀行客戶請登入</h2>
							<ul>
								<li>
									<input id="userId" name="userId" value="請輸入身分證字號" onfocus="if(this.value=='請輸入身分證字號') this.value='';" onBlur="if(this.value=='') this.value='請輸入身分證字號';" type="text"  class="text_bar w190" />
								</li>
          						<li>
									<input id="birthday" name="birthday" value="請輸入您的生日 YYYY/MM/DD" onfocus="if(this.value=='請輸入您的生日 YYYY/MM/DD') this.value='';" onBlur="if(this.value=='') this.value='請輸入您的生日 YYYY/MM/DD';" type="text"  class="text_bar w190" />
								</li>
							</ul>
							<c:choose>
								<c:when test="${error == null}">
									<p id="defMsg" class="h110">
										本服務為澳盛銀行客戶獨享，若您尚未成為本行客戶，想進一步了解活動詳情，請<a href="https://anz.tw/zh/online-contact-us/contact-us.jsp">專人與我聯絡</a>
									</p>
								</c:when>
								<c:otherwise>
									<p class="h110" style="color: red;">
										本線上投資論壇為澳盛銀行客戶獨享，若您已在澳盛銀行開立銀行帳戶，請重新輸入身分證字號或生日；若您尚未在澳盛銀行開立銀行帳戶，想進一步了解活動詳情，請<a href="https://anz.tw/zh/online-contact-us/contact-us.jsp">專人與我聯絡</a>
									</p>
								</c:otherwise>
							</c:choose>
							<a href="javascript:login();" class="btn text_none">登入</a>
						</form>
					</div>
				</div>
			</div>
			<div id="footer" class="clearfix">
				<div class="fl_box">
					<ul class="nav clearfix">
						<li class="pl_0">
							<a href="http://anz.tw/zh/security/security-1.jsp">網路安全</a>
						</li>
						<li>
							<a href="http://anz.tw/zh/terms/privacy.jsp">個人隱私保護</a>
						</li>
						<li>
							<a href="http://anz.tw/zh/terms/use-of-terms.jsp">銀行責任歸屬</a>
						</li>
						<li>
							<a href="http://anz.tw/zh/about-us/careers.jsp">加入澳盛</a>
						</li>
						<li class="line_none">
							<a href="http://anz.tw/zh/sitemap/sitemap.jsp">網站地圖</a>
						</li>
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
			function login() {
				if ($("#userId").val() == "請輸入身分證字號" || $("#userId").val() == "") {
					alert("請輸入身分證字號！");
					return;
				}
				
				$("#userId").val($("#userId").val().toUpperCase());
				
				if (!$("#userId").val().match("^[A-Z]{1}[1-2]{1}[0-9]{8}$")) {
					alert("身分證字號格式錯誤！");
					return;
				}
				if ($("#birthday").val() == "請輸入您的生日 YYYY/MM/DD" || $("#birthday").val() == "") {
					alert("請輸入您的生日！");
					return;
				}
				if (!$("#birthday").val().match("^[0-9]{4}\/[0-1]{1}[0-9]{1}\/[0-3]{1}[0-9]{1}$")) {
					alert("生日格式錯誤！");
					return;
				}
				
				$("#form").submit();
			}
		</script>
	</body>
</html>
