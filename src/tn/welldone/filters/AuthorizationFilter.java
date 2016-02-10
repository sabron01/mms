package tn.welldone.filters;

import java.io.IOException;
import java.security.Principal;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tn.welldone.model.Permission;
import tn.welldone.model.User;
import tn.welldone.service.UserBean;

@WebFilter(filterName = "authorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {

	private User user;

	@EJB
	private UserBean userBean;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String pageRequested = req.getRequestURL().toString();
		boolean isAllowed = false;
		Principal principal = req.getUserPrincipal();
		if (principal != null) {
			setUser(userBean.getUserByLogin(principal.getName()));
		}
		HttpSession session = req.getSession(true);
		chain.doFilter(req, resp);

	}

	public void init(FilterConfig config) throws ServletException {
		// Nothing to do here!
	}

	public void destroy() {
		// Nothing to do here!
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
