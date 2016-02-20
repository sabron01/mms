package tn.welldone.interceptors;

import java.util.Map;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class TaskInterceptor {
	
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {

		System.out.println("TaskInterceptor - Logging BEFORE calling method :"+context.getMethod().getName() );
		
		Map<String,Object> map = context.getContextData();
		for (Map.Entry<String, Object> entry : map.entrySet())
		{
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		}

		Object result = context.proceed();

		System.out.println("TaskInterceptor  -Logging AFTER calling method :"+context.getMethod().getName() );

		return result;
	}

}
