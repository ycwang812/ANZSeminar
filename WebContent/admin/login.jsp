<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Cache-Control" content="no-cache">
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Expires" content="0">
		<title>Seminar Admin Login</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.css" />" />
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/layout.css" />" />
		<script type="text/javascript" src="<c:url value="/js/jquery-1.9.1.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/bootstrap.js" />"></script>
		<style type="text/css">			
			.jumbotron {
				margin: 40px 0;
				text-align: center;
			}
			
			.jumbotron h1 {
				font-size: 30px;
				line-height: 1;
			}

			.form-signin {
				max-width: 300px;
				padding: 19px 29px 29px;
				margin: 0 auto 35px;
				background-color: #f5f5f5;
				border: 1px solid #e5e5e5;
				-webkit-border-radius: 5px;
				-moz-border-radius: 5px;
				border-radius: 5px;
				-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
				-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
				box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
			}

			.form-signin .form-signin-heading,.form-signin .checkbox {
				margin-bottom: 10px;
			}

			.form-signin input[type="text"],.form-signin input[type="password"] {
				font-size: 16px;
				height: auto;
				margin-bottom: 15px;
				padding: 7px 9px;
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
			</div>
			<div class="container">
				<div class="jumbotron">
					<h1>Seminar Admin Login</h1>
				</div>
				<form action="<c:url value="/admin/adminLoginAction.do" />" method="post" class="form-signin" id="form">
					<h2 class="form-signin-heading">Please sign in</h2>
					User ID：<input type="text" id="userid" name="userid" class="input-block-level" placeholder="User ID">
					Password：<input type="password" id="password" name="password" class="input-block-level" placeholder="Password">
					<div class="control-group error">
						<c:if test="${error != null}">
							<span class="help-inline">${error}</span>
						</c:if>
					</div>
					<button class="btn btn-large btn-primary" type="button" onclick="login();">Sign in</button>
				</form>
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
			function login() {
				if ($("#userid").val() == "") {
					alert("請輸入User ID！");
					$("#userid").focus();
					return;
				}
				
				if ($("#password").val() == "") {
					alert("請輸入Password！");
					$("#password").focus();
					return;
				}
			
				$("#form").submit();
			}
		</script>
	</body>
</html>