package tn.welldone.interceptors;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.naming.InitialContext;

import tn.welldone.controller.SessionUser;
import tn.welldone.helpers.Operation;
import tn.welldone.security.Permission;
import tn.welldone.security.PermissionsRequired;
import tn.welldone.service.UserBean;

public class AuthorizationSecurityInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	SessionContext context;
	
	@Inject
	SessionUser session;
	
	@Inject
	UserBean userBean;

	public SessionUser getSession() {
		return session;
	}

	public void setSession(SessionUser session) {
		this.session = session;
	}

	@AroundInvoke
	public Object checkUserRole(InvocationContext ic) throws Exception {

		Method method = ic.getMethod();
		//System.out.println("Target is : " + ic.getTarget().toString());
		Annotation[] annotations = method.getAnnotations();
		for (Annotation annotation : annotations) {
			//System.out.println("Annotation is : " + annotation.toString());
		}
		if (method.isAnnotationPresent(PermissionsRequired.class)) {
			PermissionsRequired annotation = method
					.getAnnotation(PermissionsRequired.class);
			Permission[] permissionAnnotations = annotation.value();
			if (permissionAnnotations.length != 0) {
				for (Permission permission : permissionAnnotations) {
					//System.out.println("Permision For : "+ permission.resource() + " Operation is : "+ permission.value());
				}
				return ic.proceed();
			}
			return ic.proceed();

		} else if (method.isAnnotationPresent(Permission.class)) {
			Permission permission = method.getAnnotation(Permission.class);
			String ressource = permission.resource().getSimpleName();
			Operation operation = permission.value();
			//System.out.println("Permision For : " + permission.resource()+ " Operation is : " + permission.value());
			for (tn.welldone.model.Permission userPermission : userBean.getPermissions(session.getUser())) {
				System.out.println("User Permission Resource : "+userPermission.getSystemResource().toString());
				System.out.println("Permission Resource : "+ressource);
				System.out.println("User Permission Operation : "+userPermission.getOperation().toString());
				System.out.println("Permission Operation : "+operation.toString());
				if (operation.equals(userPermission.getOperation()) && ressource.equals(userPermission.getSystemResource().toString()))
					return ic.proceed();
				else
					throw new SecurityException("User: '"
							+ session.getUser().getLogin()
							+ "' does not have permissions for method "
							+ ic.getMethod());
			}

		}
		throw new SecurityException("User: '"
				+ session.getUser().getLogin()
				+ "' does not have permissions for method "
				+ ic.getMethod());

	}

	public Object log(InvocationContext invocationContext) throws Exception {

		Principal principal = context.getCallerPrincipal();
		Map<String, Object> map = context.getContextData();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		if (!context.isCallerInRole("Admin")) {
			session.logout();
		}

		return invocationContext.proceed();
	}

}
