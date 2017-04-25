package tw.com.anz.seminar.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tw.com.anz.seminar.service.CalendarService;

public class CalendarAction extends Action {
	
	private Logger log = Logger.getLogger(getClass());
	
	private CalendarService calendarService;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String html = calendarService.parseEmbedHtml();
		
		if (html != null) {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print(html);
			response.getWriter().close();
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
		return null;
	}

	public void setCalendarService(CalendarService calendarService) {
		this.calendarService = calendarService;
	}

}
