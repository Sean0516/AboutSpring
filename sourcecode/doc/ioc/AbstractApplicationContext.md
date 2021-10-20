IOC 流程

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



1. 创建Bean 工厂 
2. 对xml 或注解进行读取或解析，并放入BeanDefinitionMap
3. 执行beanFactoryPostProcessor
4. 注册Bean 处理器。 只是注册功能
5. 初始化message 源 , 国际化处理
6. 在所有注册的bean 中查找listener bean ，注册到新消息广播器中
7. 注册监听器

```java
public void refresh() throws BeansException, IllegalStateException {
    synchronized(this.startupShutdownMonitor) {
        // 创建容器前的准备工作 
        this.prepareRefresh();
        ConfigurableListableBeanFactory beanFactory = this.obtainFreshBeanFactory();
        this.prepareBeanFactory(beanFactory); // beanFactory 准备工作，对各种属性进行填充

        try {
            this.postProcessBeanFactory(beanFactory);
            this.invokeBeanFactoryPostProcessors(beanFactory); //执行beanFactoryPostProcessor
            this.registerBeanPostProcessors(beanFactory); //注册Bean 处理器。 只是注册功能
            this.initMessageSource();
            this.initApplicationEventMulticaster();
            this.onRefresh();
            this.registerListeners(); // 在所有注册的bean 中查找listener bean ，注册到新消息广播器重
            this.finishBeanFactoryInitialization(beanFactory); // 实例化剩下的单实例
            this.finishRefresh();
        } catch (BeansException var9) {
            if (this.logger.isWarnEnabled()) {
                this.logger.warn("Exception encountered during context initialization - cancelling refresh attempt: " + var9);
            }

            this.destroyBeans();
            this.cancelRefresh(var9);
            throw var9;
        } finally {
            this.resetCommonCaches();
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

