package tw.com.anz.seminar.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tw.com.anz.seminar.Constant;

public class LogoutAction extends Action {
	
	private Logger log = Logger.getLogger(getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		return mapping.findForward(Constant.SUCCESS);
	}

}
