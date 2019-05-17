package dubburaver.spring;

import java.lang.reflect.Method;

public class InvokerHolder {

	Object proxy;
	
	String methodName;
	
	Class<?>[] parameterTypes;
	
	Object[] arguments;
	
    protected Object doInvoke(Object[] arguments) throws Exception {
    	Method method = proxy.getClass().getMethod(methodName, parameterTypes);
    	return method.invoke(proxy, arguments);
}
}
