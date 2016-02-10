package tn.welldone.interceptors;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import tn.welldone.controller.SessionUser;

public class AuthorizationSecurityInterceptor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	SessionContext context;

	@Inject
	SessionUser session;

	public SessionUser getSession() {
		return session;
	}

	public void setSession(SessionUser session) {
		this.session = session;
	}

	@AroundInvoke
	public Object log(InvocationContext invocationContext) throws Exception {

		Principal principal = context.getCallerPrincipal();
		Map<String,Object> map = context.getContextData();
		for (Map.Entry<String, Object> entry : map.entrySet())
		{
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		if (!context.isCallerInRole("Admin")) {
			session.logout();
		}
		
		return invocationContext.proceed();
	}

}
