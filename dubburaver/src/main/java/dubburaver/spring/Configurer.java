package dubburaver.spring;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.sanyinggroup.corp.urocissa.server.ServerInit;
import com.sanyinggroup.corp.urocissa.server.api.info.ClientApp;
import com.sanyinggroup.corp.urocissa.server.api.service.MsgServiceHandlerRegister;

public class Configurer implements BeanDefinitionRegistryPostProcessor, InitializingBean, ApplicationContextAware, BeanNameAware {

	private ApplicationContext applicationContext;

	private String beanName;
	
	private String interfaceName;
	
	private String actionName;

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub

	}
	
	

	public String getInterfaceName() {
		return interfaceName;
	}



	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}



	public String getActionName() {
		return actionName;
	}



	public void setActionName(String actionName) {
		this.actionName = actionName;
	}



	/**
	 * {@inheritDoc}
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setBeanName(String name) {
		this.beanName = name;
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		// SocketAddress addr = new InetSocketAddress(registryURL.getHost(),
		// registryURL.getPort());
		// socket.connect(addr, 1000);
		// host = socket.getLocalAddress().getHostAddress();
		// ;
		//
		//
		// definition.getPropertyValues().add("sqlSessionFactory", new
		// RuntimeBeanReference(this.sqlSessionFactoryBeanName));
		// beanDefinition.getPropertyValues().addPropertyValue("ref", new
		// BeanDefinitionHolder(classDefinition, id + "Impl"));
		//
		// Invoker<?> invoker = proxyFactory.getInvoker(ref, (Class)
		// interfaceClass, url);
		//
		// Exporter<?> exporter = protocol.export(invoker);
		// exporters.add(exporter);
		initMiddlerWareStart(actionName,MsgHandler.class);
	}

	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("begin......");
		BeanDefinition beanDefinitionref = registry.getBeanDefinition("msgSendImpl");
		
		int a=registry.getBeanDefinitionCount();
		System.out.println(a);
//		BeanDefinition candidate=null;
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(ServiceBean.class);
        beanDefinition.setBeanClassName(ServiceBean.class.getName());
        beanDefinition.getPropertyValues().addPropertyValue("interfaceName", actionName);
//      definition.getPropertyValues().add("sqlSessionFactory", new RuntimeBeanReference(this.sqlSessionFactoryBeanName));
        beanDefinition.getPropertyValues().addPropertyValue("ref", new BeanDefinitionHolder(beanDefinitionref, "msgSendI"));
        
        registry.registerBeanDefinition(ServiceBean.class.getName(), beanDefinition);
		int aa=registry.getBeanDefinitionCount();
		System.out.println(aa);
	}
	
	public void initMiddlerWareStart(String msgName, Class clazz) {
		System.out.println("启动服务器，在9166端口监听tcp");
		try {
			MsgServiceHandlerRegister register = MsgServiceHandlerRegister.getRegister();
			// 注册事件处理类
			register.addMsgServiceHandler(msgName, clazz);
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						// isMiddlewearStarted=true;
						java.net.InetAddress address = java.net.InetAddress.getLocalHost();// 获取的是本地的IP地址
						String hostAddress = address.getHostAddress();// 192.168.0.121
						java.util.List<ClientApp> clientAppList = new java.util.ArrayList<ClientApp>();
						ClientApp eachClient = new ClientApp();// 通讯层用的客户对象
						// "85f035102cb411e8b971002655fff888", "homer888"
						eachClient.setAppKey("85f035102cb411e8b971002655fff888");
						eachClient.setAppSecret("homer888");
						clientAppList.add(eachClient);

						ServerInit.init(9166, clientAppList);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						// isMiddlewearStarted=false;
					}
				}
			}).start();
			System.out.println("启动消息服务成功！端口：" + 9166);
		} catch (Exception e) {
//			isMiddlewearStarted = false;
			e.printStackTrace();
			System.out.println("【背调中心】启动消息服务失败！异常：" + e.toString());
		}

	}

}
