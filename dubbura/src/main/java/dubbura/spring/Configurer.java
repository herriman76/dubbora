package dubbura.spring;

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

public class Configurer implements BeanDefinitionRegistryPostProcessor, InitializingBean, ApplicationContextAware, BeanNameAware {

	private ApplicationContext applicationContext;

	private String beanName;

	private String interfaceName;

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub

	}
	
	

	public String getInterfaceName() {
		return interfaceName;
	}



	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
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

	}

	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("begin......");
		System.out.println("begin......");
		System.out.println("begin......");
		int a=registry.getBeanDefinitionCount();
		System.out.println(a);
		Set<BeanDefinitionHolder> beanDefinitions = new LinkedHashSet<BeanDefinitionHolder>();
//		BeanDefinition candidate=null;
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(ReferenceBean.class);
        beanDefinition.setBeanClassName(ReferenceBean.class.getName());
//        beanDefinition.set
//		BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(beanDefinition, "msgSendI");
//		beanDefinitions.add(definitionHolder);
//		String beanName = definitionHolder.getBeanName();
//		registry.registerBeanDefinition(beanName, definitionHolder.getBeanDefinition());
        
        registry.registerBeanDefinition("msgSendI", beanDefinition);
        
		int aa=registry.getBeanDefinitionCount();
		System.out.println(aa);
	}

}
