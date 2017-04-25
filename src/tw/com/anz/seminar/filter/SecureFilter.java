package tw.com.anz.seminar.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SecureFilter implements Filter {

	protected FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		if (request instanceof HttpServletRequest) {
			HttpSession session = ((HttpServletRequest) request).getSession(false);
			
			if (session != null) {
				String user = (String) session.getAttribute("user");
				
				if (user != null) {
					chain.doFilter(request, response);
					return;
				}
			}

			if (((HttpServletRequest) request).getServletPath().equals("/admin/adminLoginAction.do")) {
				chain.doFilter(request, response);
				return;
			}
		}

		if (filterConfig != null) {
			String login_page = filterConfig.getInitParameter("login_page");
			
			if (login_page != null && !"".equals(login_page)) {
				filterConfig.getServletContext().getRequestDispatcher(login_page).forward(request, response);
				return;
			}
		}

		throw new ServletException("Unauthorized access, unable to forward to login page");

	}

	public void destroy() {
		this.filterConfig = null;
	}

}
