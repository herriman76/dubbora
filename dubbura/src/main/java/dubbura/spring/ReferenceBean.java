package dubbura.spring;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.sanyinggroup.corp.urocissa.client.init.Client;
import com.sanyinggroup.corp.urocissa.client.init.ClientCenter;
import com.sanyinggroup.corp.urocissa.client.model.MiddleMsg;
import com.sanyinggroup.corp.urocissa.client.model.ResultObject;



public class ReferenceBean<T>  implements FactoryBean, ApplicationContextAware, InitializingBean, DisposableBean {

	
    private static final long serialVersionUID = 213195494150089726L;

    private transient ApplicationContext applicationContext;
    
    // 接口类型
    private String interfaceName=MsgSendI.class.getName();
    
    private Class<?> interfaceClass=MsgSendI.class;
    
    // 接口代理类引用
    private transient volatile T ref;
    private transient volatile InvocationHandler invoker;
    
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("get the proxy object");
		InvocationHandler invocationHandler=new InvocationHandler(){

			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("method invoke:"+method.getName());
				System.out.println("method invoke:"+args[0].toString());
//				Client client=ClientCenter.getAClient("192.168.110.130", 9080, "85f035102cb411e8b971002655fff888", "homer888",null);
				Client client=ClientCenter.getAClient("192.168.117.35", 9166, "85f035102cb411e8b971002655fff888", "homer888",null);
				MiddleMsg msg = new MiddleMsg(method.getName(), args[0].toString());

				//下面是发异步消息
				//ResultObject result=client.sendMsg(msg, new MyMsgHandler());
				
				
				//下面是发同步消息
				MiddleMsg resultMsg=client.sendMsgSync(msg);
				
				
				System.out.println("【收到消息：】"+resultMsg.getBody());
				System.out.println("【收到消息：】"+resultMsg.getBody());
				System.out.println("【收到消息：】"+resultMsg.getBody());
				
				ResultObject result=new ResultObject();
				Map map=new HashMap();
				map.put("data",resultMsg.getBody());
				result.setAttachment(map);
//				return method.invoke(this, args);
				return result;
			}
			
		};
		invoker=invocationHandler;
		ref =(T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass }, invocationHandler);
		System.out.println("has set the Proxy Bean");
	}

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

	public Object getObject() throws Exception {
		// TODO Auto-generated method stub
		return ref;
	}

	public Class getObjectType() {
		// TODO Auto-generated method stub
		return interfaceClass;
	}

	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

}
