package tw.com.anz.seminar.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.springframework.web.bind.ServletRequestUtils;

import tw.com.anz.seminar.Constant;
import tw.com.anz.seminar.model.Event;
import tw.com.anz.seminar.model.Question;
import tw.com.anz.seminar.model.Report;
import tw.com.anz.seminar.service.SecurityService;
import tw.com.anz.seminar.service.SeminarService;
import tw.com.anz.seminar.util.FileUtil;

public class SeminarAction extends MappingDispatchAction {

	private Logger log = Logger.getLogger(getClass());

	private SeminarService seminarService;
	private SecurityService securityService;
	private String account;
	
	public ActionForward home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("headlineEvent", seminarService.queryHeadlineEvent());
		request.setAttribute("eventList", seminarService.findRecentRecordEvent());
		request.setAttribute("account", account);
		
		return mapping.findForward(Constant.SUCCESS);
	}

	public ActionForward watch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String eventId = ServletRequestUtils.getStringParameter(request, "v");
		
		log.debug("eventId: " + eventId);
		
		Event event = seminarService.queryEvent(eventId);
		
		if (event.getLogin().equals(Constant.YES)) {
			request.setAttribute("v", eventId);
			return mapping.findForward(Constant.LOGIN);
		}
		
		request.setAttribute("eventList", seminarService.findRecentRecordEvent());
		request.setAttribute("event", seminarService.queryEvent(eventId));
		request.setAttribute("openTime", System.currentTimeMillis());
		request.setAttribute("userId", "Guest");
		
		return mapping.findForward(Constant.SUCCESS);
	}
	
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userId = ServletRequestUtils.getStringParameter(request, "userId");
		String birthday = ServletRequestUtils.getStringParameter(request, "birthday");
		String eventId = ServletRequestUtils.getStringParameter(request, "v");
		
		log.debug("userId: " + userId + ", birthday: " + birthday + ", eventId: " + eventId);
		
		String memberNo = securityService.checkUser(userId, birthday);
		
		log.debug("memberNo: " + memberNo);
		
		if (memberNo == null) {
			request.setAttribute("error", Constant.YES);
			request.setAttribute("v", eventId);
			return mapping.findForward(Constant.LOGIN);
		}
		
		request.setAttribute("eventList", seminarService.findRecentRecordEvent());
		request.setAttribute("event", seminarService.queryEvent(eventId));
		request.setAttribute("openTime", System.currentTimeMillis());
		request.setAttribute("userId", memberNo);
		
		return mapping.findForward(Constant.SUCCESS);
	}

	public ActionForward queryYouTube(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		long id = ServletRequestUtils.getLongParameter(request, "id");

		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().print(seminarService.queryYouTubeId(id));
		response.getWriter().close();
		
		return null;
	}

	public ActionForward saveQuestion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long eId = ServletRequestUtils.getLongParameter(request, "id");
		String userId = ServletRequestUtils.getStringParameter(request, "userId");
		String q = ServletRequestUtils.getStringParameter(request, "question");
		
		Question question = new Question();
		question.seteId(eId);
		question.setUserId(userId);
		question.setQuestion(q);
		question.setTime(new Date());
		
		seminarService.saveQuestion(question);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().print(Constant.SUCCESS);
		response.getWriter().close();
		
		return null;
	}

	public ActionForward report(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long eId = ServletRequestUtils.getLongParameter(request, "id");
		String userId = ServletRequestUtils.getStringParameter(request, "userId");
		long openTime = ServletRequestUtils.getLongParameter(request, "openTime");
		double watchTime = ServletRequestUtils.getDoubleParameter(request, "watchTime");
		
		Report report = new Report();
		report.setId(openTime);
		report.seteId(eId);
		report.setUserId(userId);
		report.setOpenTime(new Date(openTime));
		report.setWatchTime((long) watchTime);
		
		seminarService.saveOrUpdateReport(report);
		
		return null;
	}
	
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String eventId = ServletRequestUtils.getStringParameter(request, "v");
		
		log.debug("eventId: " + eventId);
		
		response.setContentType("application/ics; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + FileUtil.ICS_FILE_NAME);

		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			out.write(FileUtil.genIcsFile(seminarService.queryEvent(eventId)));
			out.flush();
		} catch (Exception ex) {
			log.error("export", ex);
		}
		
		return null;
	}

	public void setSeminarService(SeminarService seminarService) {
		this.seminarService = seminarService;
	}
	
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
