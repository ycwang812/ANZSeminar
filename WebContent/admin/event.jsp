<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Seminar Maintenance</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.css" />" />
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/jquery.timepicker.css" />" />
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/base.css" />" />
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/layout.css" />" />
		<script type="text/javascript" src="<c:url value="/js/jquery-1.9.1.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/bootstrap.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.timepicker.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/datepair.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/base.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.upload-1.0.2.js" />"></script>
		<style type="text/css">
			.form-horizontal {
				margin-top: 20px;
			}
		
			.ui-timepicker-list {
				width: 9em;
			}
			
			#introduction {
				width: 30em;
			}
			
			#legal {
				width: 30em;
				height: 6em;
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
				<form action="" method="post" id="eventForm" class="form-horizontal">
					<input type="hidden" id="id" name="id" value="${event.id}" />
					<input type="hidden" id="eventId" name="eventId" value="${event.eventId}" />
					<input type="hidden" id="uploadImage" name="uploadImage" value="${event.uploadImage}" />
					<input type="hidden" id="uploadFile" name="uploadFile" value="${event.uploadFile}" />
					<fieldset>
						<legend>Seminar Maintenance <small>(*星號為必填欄位)</small></legend>
						<div class="control-group">
							<label class="control-label">Seminar Name*</label>
							<div class="controls">
								<input type="text" id="summary" name="summary" class="input-xlarge" value="${event.summary}" />
								&nbsp;&nbsp;(全行中文10字，半行英數字20字，含空格)
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Seminar Date/Time*</label>
							<div class="controls">
								<div class="datepair" data-language="javascript">
									<input type="text" class="date start" id="startDate" name="startDate" value="<fmt:formatDate value='${event.startTime}' pattern='yyyy-MM-dd'/>" />
									<input type="text" class="time start" id="startTime" name="startTime" value="<fmt:formatDate value='${event.startTime}' pattern='HH:mm:ss'/>" />
									&nbsp;&nbsp;till&nbsp;&nbsp;
									<input type="text" class="date end" id="endDate" name="endDate" value="<fmt:formatDate value='${event.endTime}' pattern='yyyy-MM-dd'/>" />
									<input type="text" class="time end" id="endTime" name="endTime" value="<fmt:formatDate value='${event.endTime}' pattern='HH:mm:ss'/>" />
									&nbsp;&nbsp;(YYYY-MM-DD，HH:MM:SS)
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Introduction of seminar*</label>
							<div class="controls">
								<textarea id="introduction" name="introduction">${event.introduction}</textarea>
								&nbsp;&nbsp;(全行中文110字，半行英數字220字，含空格)
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Speaker</label>
							<div class="controls">
								<input type="text" id="speaker" name="speaker" class="input-xlarge" value="${event.speaker}" />
								&nbsp;&nbsp;(全行中文30字內，半行英數字60字，含空格)
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								<label class="radio inline" for="hangout">
									<input type="radio" id="seminarKind" name="seminarKind" value="hangout" <c:if test="${event.seminarKind eq 'hangout'}">checked</c:if> />
									Live
								</label>
							</label>
							<div class="controls">
								<label>YouTube URL</label>
								<input type="text" name="youtubeUrl" class="input-xlarge" value="${event.youtubeUrl}" />
								<br>
								<br>
								<label>Privacy*</label>
								<select id="privacyStatus" name="privacyStatus" class="input-xlarge">
									<option value="unlisted" <c:if test="${event.privacyStatus eq 'unlisted'}">selected</c:if>>Unlisted</option>
									<option value="public" <c:if test="${event.privacyStatus eq 'public'}">selected</c:if>>Public</option>				
									<option value="private" <c:if test="${event.privacyStatus eq 'private'}">selected</c:if>>Private</option>
								</select>
								<br>
								<br>
								<label>Raise question via on-line (Y/N)*</label>
								<select id="question" name="question" class="input-xlarge">
									<option value="">Please select</option>
									<option value="Y" <c:if test="${event.question eq 'Y'}">selected</c:if>>Yes</option>
									<option value="N" <c:if test="${event.question eq 'N'}">selected</c:if>>No</option>
								</select>
							</div>
						</div>
						<div class="control-group"></div>
						<div class="control-group">
							<label class="control-label">
								<label class="radio inline">
									<input type="radio" id="seminarKind" name="seminarKind" value="record" <c:if test="${event.seminarKind eq 'record'}">checked</c:if> />
									Video
								</label>
							</label>
							<div class="controls">
								<label>Video URL*</label>
								<input type="text" id="url" name="url" class="input-xlarge" value="${event.url}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Attachment*</label>
							<div class="controls">
								<label id="newImage">
									<input type="file" id="image" name="image" class="input-file" value="${event.uploadImage}" />
									&nbsp;&nbsp;(picture for seminar, 350px X 255px JPG/PNG file)
								</label>
								<label id="updateImage">
									${event.uploadImage}
									<input type="button" value="Re-Upload" onclick="changeImage(true);" />
									&nbsp;&nbsp;(picture for seminar, 350px X 255px JPG/PNG file)
								</label>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Legal terms*</label>
							<div class="controls">
								<c:choose>
									<c:when test="${legal != null}">
										<textarea id="legal" name="legal">${legal}</textarea>
									</c:when>
									<c:otherwise>
										<textarea id="legal" name="legal">${event.legal}</textarea>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Seminar summary</label>
							<div class="controls">
								<label id="newFile">
									<input type="file" id="file" name="file" class="input-file" value="${event.uploadFile}" />
								</label>
								<label id="updateFile">
									${event.uploadFile}
									<input type="button" value="Re-Upload" onclick="changeFile(true);" />
								</label>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Duration</label>
							<div class="controls">
								<input type="text" id="duration" name="duration" class="input-xlarge" value="${event.duration}" />
								&nbsp;&nbsp;(單位為分鐘，範圍為1~999999)
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Reset layout</label>
							<div class="controls">
								 <label class="checkbox inline">
								 	<input type="checkbox" id="headline" name="headline" value="Y" <c:if test="${event.headline eq 'Y'}">checked</c:if> />
								 	Headline
								 </label>
								 <label class="checkbox inline">
								 	<input type="checkbox" id="top" name="top" value="Y" <c:if test="${event.top eq 'Y'}">checked</c:if> />
								 	Top
								 </label>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Log-in Setting*</label>
							<div class="controls">
								<select id="login" name="login" class="input-xlarge">
									<option value="N" <c:if test="${event.login eq 'N'}">selected</c:if>>Non log-in</option>
									<option value="Y" <c:if test="${event.login eq 'Y'}">selected</c:if>>Log-in</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button class="btn btn-info" type="button" onclick="save();">Save</button>
								&nbsp;&nbsp;
								<button class="btn btn-success" type="button" onclick="back();">Back</button>
							</div>
						</div>
					</fieldset>
				</form>
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
				if ($("#eventId").val() == "") {
					$("#seminarKind").attr("checked", "hangout");
				}
				
				if ($("#uploadImage").val() == "") {
					changeImage(true);
				} else {
					changeImage(false);
				}
				
				if ($("#uploadFile").val() == "") {
					changeFile(true);
				} else {
					changeFile(false);
				}
				
				$("#image").change(function() {
					$(this).upload("<c:url value="/admin/uploadImageAction.do" />", function(data) {
						if (data != "") {
							$("#uploadImage").val(data);
						} else {
							alert("Upload Attachment fail！");
						}
					});
				});
				
				$("#file").change(function() {
					$(this).upload("<c:url value="/admin/uploadFileAction.do" />", function(data) {
						if (data != "") {
							$("#uploadFile").val(data);
						} else {
							alert("Upload Seminar summary fail！");
						}
					});
				});
			});
			
			function changeImage(isNew) {
				if (isNew == true) {
					$("#newImage").show();
					$("#updateImage").hide();
				} else {
					$("#newImage").hide();
					$("#updateImage").show();
				}
			}
			
			function changeFile(isNew) {
				if (isNew == true) {
					$("#newFile").show();
					$("#updateFile").hide();
				} else {
					$("#newFile").hide();
					$("#updateFile").show();
				}
			}
			
			function save() {
				if ($("#summary").val() == "") {
					alert("Please enter the Seminar Name！");
					$("#summary").focus();
					return;
				}
				if ($("#startDate").val() == "") {
					alert("Please enter the Start Seminar Date！");
					$("#startDate").focus();
					return;
				}
				if ($("#startTime").val() == "") {
					alert("Please enter the Start Seminar Time！");
					$("#startTime").focus();
					return;
				}
				if ($("#endDate").val() == "") {
					alert("Please enter the End Seminar Date！");
					$("#endDate").focus();
					return;
				}
				if ($("#endTime").val() == "") {
					alert("Please enter the End Seminar Time！");
					$("#endTime").focus();
					return;
				}
				if ($("input[name=seminarKind]:checked").val() == "hangout") {
					if ($("#question").val() == "") {
						alert("Please select Raise question via on-line！");
						$("#question").focus();
						return;
					}
				} else if ($("input[name=seminarKind]:checked").val() == "record") {
					if ($("#url").val() == "") {
						alert("Please enter the Video URL！");
						$("#url").focus();
						return;
					}
				}
				if ($("#uploadImage").val() == "") {
					alert("Please upload attachment！");
					$("#image").focus();
					return;
				}
				if ($("#legal").val() == "") {
					alert("Please enter the Legal terms！");
					$("#legal").focus();
					return;
				}
				
				if ($("#eventId").val() == "") {
					$("#eventForm").attr("action", "<c:url value="/admin/saveEventAction.do" />");
				} else {
					$("#eventForm").attr("action", "<c:url value="/admin/updateEventAction.do" />");
				}
				
				$("#eventForm").submit();
			}

			function back() {
				$("#eventForm").attr("action", "<c:url value="/admin/adminHomeAction.do" />");
				$("#eventForm").submit();
			}
		</script>
	</body>
</html>