package tn.welldone.ressources;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class CallTracer {
    @AroundInvoke
    public Object trace(InvocationContext ic) throws Exception {
        System.out.println("-- " + ic.getMethod());
        return ic.proceed();
    }
}
