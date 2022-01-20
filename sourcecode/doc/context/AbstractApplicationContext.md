### refresh 方法

创建环境对象 ，包含系统属性

```java
// 调用父类构造方法,进行相关对象的创建等操作
super(parent);
// 设置配置文件的路径
setConfigLocations(configLocations);


	protected void customizePropertySources(MutablePropertySources propertySources) {
		propertySources.addLast(
				new PropertiesPropertySource(SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME, getSystemProperties()));
		propertySources.addLast(
				new SystemEnvironmentPropertySource(SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, getSystemEnvironment()));
	}
```

1. prepareRefresh()准备工作 
   1. 设置容器的启动事件
   2. 设置活跃状态为true

2. obtainFreshBeanFactory（）创建Bean 工厂 对xml 或注解进行读取或解析，并放入BeanDefinitionMap
3. repareBeanFactory(beanFactory) 给容器对象进行某些初始化操作 （BeanFactory进行一些设置，比如context的类加载器，BeanPostProcessor和XXXAware自动装配等
4. postProcessBeanFactory  BeanFactory准备工作完成后进行的后置处理工作
5. invokeBeanFactoryPostProcessors(beanFactory) 执行BeanFactoryPostProcessor的扩展工作 
6. 初始化对象前的准备工作  (initMessageSource  initApplicationEventMulticaster  registerListeners )
   1. 注册BeanPostPressor。 只是注册功能
   2. 初始化广播器
   3. 初始化message 源 , 国际化处理
   4. 注册监听器
7. 对象实例化操作  (finishBeanFactoryInitialization  初始化所有剩下的非懒加载的单例bean) 
   1. 自定义属性
   2. 容器属性赋值
   3. 调用benPostProcessor before 前置处理方法进行扩展
   4. 调用init- method 进行初始化方法的调用
   5. 调用 benPostProcessor before 后置方法进行扩展
8. 销毁
   1. 如果 bean 实现DisposableBean 接口，当 spring 容器关闭时，会调用 destory()。
   2. 如果为bean 指定了 destroy 方法（ 的 destroy-method 属性），那么将调用它

refresh 的方法执行顺序图

![image-20220120191056228](https://gitee.com/Sean0516/image/raw/master/img/image-20220120191056228.png)

```java
public void refresh() throws BeansException, IllegalStateException {
    synchronized(this.startupShutdownMonitor) {
      // Prepare this context for refreshing.
			/**
			 * 做容器属性前的准备工作
			 * 1. 设置容器的启动时间
			 * 2. 设置活跃状态为true
			 * 3. 设置关闭状态为false
			 * 4. 获取Environment 对象，添加当前系统属性到 Environment 对象中
			 * 5. 准备监听器和事件的集合对象，默认为空的集合
			 */
			prepareRefresh();
			// 创建BeanFactory 同时，加载xml配置文件或者注解进行BeanDefinition的解析和注册
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
			// BeanFactory的预准备工作（BeanFactory进行一些设置，比如context的类加载器，BeanPostProcessor和XXXAware自动装配等）
			prepareBeanFactory(beanFactory);

			try {
				//BeanFactory准备工作完成后进行的后置处理工作
				postProcessBeanFactory(beanFactory);
				// 执行BeanFactoryPostProcessor的方法
				invokeBeanFactoryPostProcessors(beanFactory);
				// 注册BeanPostProcessor Bean 的前置后置处理器
				registerBeanPostProcessors(beanFactory);
				// 初始化国际化消息
				initMessageSource();
				// 初始化事件处理器
				initApplicationEventMulticaster();
				// 子类重写这个方法，在容器刷新的时候可以自定义逻辑；如创建Tomcat，Jetty等WEB服务器
				onRefresh();
				// 注册应用的监听器 就是注册实现了ApplicationListener接口的监听器bean，这些监听器是注册到ApplicationEventMulticaster中的
				registerListeners();
				//初始化所有剩下的非懒加载的单例bean
				finishBeanFactoryInitialization(beanFactory);
				// 完成context的刷新。主要是调用LifecycleProcessor的onRefresh()方法，并且发布事件（ContextRefreshedEvent）
				finishRefresh();
			}
    }
}

protected void prepareRefresh() {
        this.startupDate = System.currentTimeMillis(); // 设置容器启动时间
        this.closed.set(false);
        this.active.set(true);
        if (this.logger.isDebugEnabled()) {
            if (this.logger.isTraceEnabled()) {
                this.logger.trace("Refreshing " + this);
            } else {
                this.logger.debug("Refreshing " + this.getDisplayName());
            }
        }

        this.initPropertySources();
        this.getEnvironment().validateRequiredProperties(); // 获取environment对象，并且加载当前系统的属性值到environment对象中
        if (this.earlyApplicationListeners == null) {
            this.earlyApplicationListeners = new LinkedHashSet(this.applicationListeners);
        } else {
            this.applicationListeners.clear();
            this.applicationListeners.addAll(this.earlyApplicationListeners);
        }

        this.earlyApplicationEvents = new LinkedHashSet(); // 准备监听器和事件集合对象
    }
	//  创建bean 工厂
    protected final void refreshBeanFactory() throws BeansException {
        if (this.hasBeanFactory()) {
            this.destroyBeans();
            this.closeBeanFactory();
        }

        try {
            DefaultListableBeanFactory beanFactory = this.createBeanFactory(); // 创建bean 工厂
            beanFactory.setSerializationId(this.getId()); // 设置序列化id
            this.customizeBeanFactory(beanFactory); // 定制beanFactory 设置相关属性, 包亏是否允许覆盖同名称的不同定义的对象以及循环依赖
            this.loadBeanDefinitions(beanFactory); //进行xml 文件读取和解析 ，放入BeanDefinitionMap
            this.beanFactory = beanFactory;
        } catch (IOException var2) {
            throw new ApplicationContextException("I/O error parsing bean definition source for " + this.getDisplayName(), var2);
        }
    }

```



### 循环依赖

​	使用三级缓存解决（实例化和初始化分开处理，提前暴露对象）  ，  set 方式可以解决循环依赖（set 先创建对象，再进行赋值） ，而构造方法是没有办法解决循环依赖问题的

