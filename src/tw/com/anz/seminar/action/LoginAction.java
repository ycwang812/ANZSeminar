package tw.com.anz.seminar.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;

import tw.com.anz.seminar.Constant;
import tw.com.anz.seminar.service.SecurityService;

public class LoginAction extends Action {
	
	private Logger log = Logger.getLogger(getClass());
	
	private SecurityService securityService;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userId = ServletRequestUtils.getStringParameter(request, "userid");
		String password = ServletRequestUtils.getStringParameter(request, "password");
		
		log.debug("userId: " + userId + ", password: " + password);
		
		String userName = securityService.checkAdminUser(userId, password);
		// ANZ
//		String userName = userId;
		
		if (userName == null) {
			request.setAttribute("error", "Invalid user id or password.");
			return mapping.findForward("fail");
		}
		
		log.debug("Login user: " + userName);

		request.getSession().setAttribute("user", userName);

		return mapping.findForward(Constant.SUCCESS);
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

}
