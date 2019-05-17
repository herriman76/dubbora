package dubburaver.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class ServiceBean<T> implements InitializingBean, DisposableBean, ApplicationContextAware, ApplicationListener, BeanNameAware {

	private static final long serialVersionUID = 213195494150089726L;

	private static transient ApplicationContext SPRING_CONTEXT;

	private transient ApplicationContext applicationContext;

	private transient String beanName;

	// 接口类型
	private String interfaceName;
	private Class<?> interfaceClass;
	// 接口实现类引用
	private T ref;
	
    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }

    
    
	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public Class<?> getInterfaceClass() {
		return interfaceClass;
	}

	public void setInterfaceClass(Class<?> interfaceClass) {
		this.interfaceClass = interfaceClass;
	}

	public String getBeanName() {
		return beanName;
	}

	@Override
	public void setBeanName(String name) {
		// TODO Auto-generated method stub
		this.beanName = name;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;

	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterPropertiesSet() throws Exception {

//		// 获取应用client基本信息
//		MsgServiceHandlerRegister register = MsgServiceHandlerRegister.getRegister();
//		// 注册事件处理类
//		register.addMsgServiceHandler(beanName, Msg);
//		// System.out.println("2.【背调中心】注册客户心跳消息回调处理成功");
		System.out.println("【serviceBean】afterPropertiesSet...");
		System.out.println("【serviceBean】interfaceName..."+interfaceName);
		
		InvokerHolder invokerHolder=new InvokerHolder();
		invokerHolder.methodName=interfaceName;
		invokerHolder.parameterTypes=new Class[]{String.class};
		invokerHolder.proxy=ref;
		
		System.out.println("【serviceBean】ref..."+(ref==null?"isnull":ref));

		MsgHandler.invoderMap.put(interfaceName, invokerHolder);
		

	}


}
