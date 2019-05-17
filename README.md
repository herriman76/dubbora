# dubbora
模仿dubbo，用spring的代理类实现一个远程调用，仅验证功能的测试项目。

我们已经有了一个消息中间件，我想进一步模仿dubbo，在客户端实现一个接口的代理，把请求包装成消息发给服务端。
服务端根据invocation找到真正的实现类进行处理，最后返回结果。
这样客户端调用远程方法等同于调用本地方法。


与dubbo不同，我没有用spring中的xml自定义标签，感觉太复杂，我关注实现核心功能。
也没用注解接口的方式。而是参考了mybatis的方式实现的。
mybatis也是把接口转换成对一个数据库的操作。

主要的类：
客户端
 dubbora/dubbura/src/main/java/dubbura/spring/ReferenceBean.java
public class ReferenceBean<T>  implements FactoryBean, ApplicationContextAware, InitializingBean, DisposableBean{..}
  在afterPropertiesSet()中产生了一个代理接口实现类。
  
服务端
 dubbora/dubburaver/src/main/java/dubburaver/spring/ServiceBean.java
public class ServiceBean<T> implements InitializingBean, DisposableBean, ApplicationContextAware, ApplicationListener, BeanNameAware {..}
  在afterPropertiesSet()中进行了服务暴露。
  
