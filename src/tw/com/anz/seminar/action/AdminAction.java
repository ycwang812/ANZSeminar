package tw.com.anz.seminar.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.bind.ServletRequestUtils;

import tw.com.anz.seminar.Constant;
import tw.com.anz.seminar.model.Event;
import tw.com.anz.seminar.model.Question;
import tw.com.anz.seminar.service.AdminService;
import tw.com.anz.seminar.util.DateUtil;
import tw.com.anz.seminar.util.RequestUtil;
import tw.com.anz.seminar.util.UrlUtil;

public class AdminAction extends MappingDispatchAction {
	
	private Logger log = Logger.getLogger(getClass());
	
	private AdminService adminService;
	private String account;
	private String legal;
	
	public ActionForward home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("eventList", adminService.getAllEvent());
		request.setAttribute("account", account);
		
		return mapping.findForward(Constant.SUCCESS);
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("legal", legal);
		
		return mapping.findForward(Constant.SUCCESS);
	}
	
	public ActionForward clearTop(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		adminService.clearTop();
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().print(Constant.SUCCESS);
		response.getWriter().close();
		
		return null;
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String user = (String) request.getSession().getAttribute("user");
		
		adminService.saveEvent(RequestUtil.getEventParameter(request), user);
		
		return mapping.findForward(Constant.SUCCESS);
	}
	
	public ActionForward doUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long id = ServletRequestUtils.getLongParameter(request, "id", 0);
		
		log.debug("id: " + id);
		
		if (id == 0) {
			return mapping.findForward(Constant.FAIL);
		}
		
		request.setAttribute("event", adminService.queryEvent(id));
		
		return mapping.findForward(Constant.SUCCESS);
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String user = (String) request.getSession().getAttribute("user");
		
		adminService.updateEvent(RequestUtil.getEventParameter(request), user);
		
		return mapping.findForward(Constant.SUCCESS);
	}
	
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long id = ServletRequestUtils.getLongParameter(request, "id", 0);
		
		log.debug("id: " + id);
		
		if (id == 0) {
			return mapping.findForward(Constant.FAIL);
		}
		
		Event event = adminService.queryEvent(id);
		
		request.setAttribute("event", event);
		request.setAttribute("icsUrl", UrlUtil.getIcsUrl(event.getEventId()));
		
		return mapping.findForward(Constant.SUCCESS);
	}
	
	public ActionForward question(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long id = ServletRequestUtils.getLongParameter(request, "id", 0);
		
		log.debug("id: " + id);
		
		if (id == 0) {
			return mapping.findForward(Constant.FAIL);
		}

		request.setAttribute("questionList", adminService.queryQuestion(id));
		request.setAttribute("event", adminService.queryEvent(id));
		
		return mapping.findForward(Constant.SUCCESS);
	}
	
	public ActionForward reloadQuestion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long id = ServletRequestUtils.getLongParameter(request, "id", 0);
		
		log.debug("id: " + id);
		
		List<Question> questionList = adminService.queryQuestion(id);
		
		JSONArray array = new JSONArray();
		
		for (Question question : questionList) {
			JSONObject object = new JSONObject();
			object.put("time", DateUtil.formatSlashDateTime(question.getTime()));
			object.put("userId", question.getUserId());
			object.put("question", question.getQuestion());
			
			array.put(object);
		}
		
		log.debug("JSONArray: " + array.toString());
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(array.toString());
		response.getWriter().close();
		
		return null;
	}
	
	public ActionForward report(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long id = ServletRequestUtils.getLongParameter(request, "id", 0);
		String condition = ServletRequestUtils.getStringParameter(request, "condition", null);
		
		log.debug("id: " + id + ", condition:" + condition);
		
		if (id == 0) {
			return mapping.findForward(Constant.FAIL);
		}
		
		long times = adminService.countViews(id, condition);
		String total = times != 0 ? adminService.sumWatchTime(id, condition) : "0";
		String avg = adminService.averageWatchTime(total, times);
		
		log.debug("times: " + times + ", total:" + total + ", ang:" + avg);
		
		request.setAttribute("times", times);
		request.setAttribute("total", total);
		request.setAttribute("avg", avg);
		request.setAttribute("reportList", adminService.queryTop10Report(id));
		request.setAttribute("event", adminService.queryEvent(id));
		request.setAttribute("condition", condition);
		
		return mapping.findForward(Constant.SUCCESS);
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long id = ServletRequestUtils.getLongParameter(request, "id", 0);
		String eventId = ServletRequestUtils.getStringParameter(request, "eventId", null);
		
		adminService.deleteEvent(id, eventId);
		
		return mapping.findForward(Constant.SUCCESS);
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}
	
}
