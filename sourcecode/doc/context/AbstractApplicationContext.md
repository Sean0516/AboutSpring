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
3. repareBeanFactory(beanFactory) 给容器对象进行某些初始化操作 （BeanFactory进行一些设置，比如context的类加载器，BeanPostProcessor和XXXAware，@Autowired 自动装配等
4. postProcessBeanFactory  BeanFactory准备工作完成后进行的后置处理工作
5. invokeBeanFactoryPostProcessors(beanFactory) 执行BeanFactoryPostProcessor的扩展工作 
6. 初始化对象前的准备工作  (initMessageSource  initApplicationEventMulticaster  registerListeners )
   1. 注册BeanPostPressor。 只是注册功能
   2. 初始化广播器,并放入 applicationEventMulticaster bean 中
   3. 初始化message 源 , 国际化处理
   4. 注册监听器
7. 对象实例化操作  (finishBeanFactoryInitialization  初始化所有剩下的非懒加载的单例bean) 
   1. 自定义属性
   2. 容器属性赋值
   3. 调用benPostProcessor before 前置处理方法进行扩展
   4. 调用init- method 进行初始化方法的调用
   5. 调用 benPostProcessor before 后置方法进行扩展
8. 完成刷新过程，通知生命周期处理器LifecycleProcessor的onRefresh()方法，并且发布事件（ContextRefreshedEvent）
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
			 * 设置容器的启动时间
			 * 设置活跃状态为true
			 * 设置关闭状态为false
			 * 初始化资源属性，留给子类进行扩展
			 * 获取Environment 对象，对Environment 进行校验
			 * 准备监听器和事件的集合对象，默认为空的集合
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
```

### 环境准备prepareRefresh

- 设置容器的启动时间
- 设置活跃状态为true
- 设置关闭状态为false
- 初始化资源属性，留给子类进行扩展
- 获取Environment 对象，对Environment 进行校验
- 准备监听器和事件的集合对象，默认为空的集合

```java
	protected void prepareRefresh() {
		// Switch to active.
		this.startupDate = System.currentTimeMillis();// 设置容器启动事件
		this.closed.set(false); // 设置关闭标志位
		this.active.set(true);// 设置容器的激活标志位

		if (logger.isDebugEnabled()) {
			if (logger.isTraceEnabled()) {
				logger.trace("Refreshing " + this);
			}
			else {
				logger.debug("Refreshing " + getDisplayName());
			}
		}
		initPropertySources(); // 初始化资源属性。 留给子类进行扩展
		getEnvironment().validateRequiredProperties(); // 对Environment 进行校验

		if (this.earlyApplicationListeners == null) { // 判断刷新前的应用程序监听集合是否为空，如果为空 ，则把监听器添加到此集合
			this.earlyApplicationListeners = new LinkedHashSet<>(this.applicationListeners);
		}
		else {
			this.applicationListeners.clear();
			this.applicationListeners.addAll(this.earlyApplicationListeners);
		}
		this.earlyApplicationEvents = new LinkedHashSet<>();
	}

```

### obtainFreshBeanFactory 加载BeanFactory ，解析和注册BeanDefinition

![image-20220121111826842](https://gitee.com/Sean0516/image/raw/master/img/image-20220121111826842.png)

#### 具体步骤

1. 创建DefaultListableBeanFactory
2. 指定序列号ID
3. 自定义BeanFactory 相关属性
4. 解析及注册BeanDefinition
5. 使用全局变量记录BeanFactory 类实例 

```java
protected ConfigurableListableBeanFactory obtainFreshBeanFactory() {
   // 初始化BeanFactory 并进行XML文件读取。
   refreshBeanFactory();
   // 返回当前实体的BeanFactory 属性
   return getBeanFactory();
}
//  创建bean 工厂
protected final void refreshBeanFactory() throws BeansException {
		if (hasBeanFactory()) {
			// 如果存在beanFactory 则销毁
			destroyBeans();
			closeBeanFactory();
		}
		try {
			// 创建DefaultListableBeanFactory 工厂
			DefaultListableBeanFactory beanFactory = createBeanFactory();
			// 为容器设置序列号id
			beanFactory.setSerializationId(getId());
			// 定制 BeanFactory ，设置相关属性,包括是否允许覆盖同名称的不同定义的对象以及循环依赖以及设置@Autowired 和Qualifier 注解解析器
			customizeBeanFactory(beanFactory);
			// 初始化BeanDefinitionDocumentReader 进行BeanDefinition 的解析和注册例如Xml BeanDefinitionDocumentReader 和AnnotationBeanDefinitionDocumentReader
			loadBeanDefinitions(beanFactory);
			this.beanFactory = beanFactory;
		}
		catch (IOException ex) {
			throw new ApplicationContextException("I/O error parsing bean definition source for " + getDisplayName(), ex);
		}
	}
```

#### 加载BeanDefinition  loadBeanDefinitions 

```java
@Override
protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException {
   // Create a new XmlBeanDefinitionReader for the given BeanFactory.
   // 创建一个XmlBeanDefinitionReader ,通过回调设置到BeanFactory 中
   XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

   // Configure the bean definition reader with this context's
   // resource loading environment.
   // 给Reader对象设置环境对象
   beanDefinitionReader.setEnvironment(this.getEnvironment());
   beanDefinitionReader.setResourceLoader(this);
   beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(this));

   // Allow a subclass to provide custom initialization of the reader,
   // then proceed with actually loading the bean definitions.
   //初始化 BeanDefinitionReader
   // 初始化BeanDefinitionRead 对象。 此处设置配置文件是否需要进行验证
   initBeanDefinitionReader(beanDefinitionReader);
   // 开始完成BeanDefinition的加载
   loadBeanDefinitions(beanDefinitionReader);
}
```

```java
public int loadBeanDefinitions(EncodedResource encodedResource) throws BeanDefinitionStoreException {
   Assert.notNull(encodedResource, "EncodedResource must not be null");
   if (logger.isTraceEnabled()) {
      logger.trace("Loading XML bean definitions from " + encodedResource);
   }
   // 通过属性来记录已经加载的资源
   Set<EncodedResource> currentResources = this.resourcesCurrentlyBeingLoaded.get();

   if (!currentResources.add(encodedResource)) {
      throw new BeanDefinitionStoreException(
            "Detected cyclic loading of " + encodedResource + " - check your import definitions!");
   }
   // 从encodedResource 获取 resource 并且获取其中的InputStream
   try (InputStream inputStream = encodedResource.getResource().getInputStream()) {
      InputSource inputSource = new InputSource(inputStream);
      if (encodedResource.getEncoding() != null) {
         inputSource.setEncoding(encodedResource.getEncoding());
      }
      // 进入真正的核心逻辑部分
      return doLoadBeanDefinitions(inputSource, encodedResource.getResource());
   }
   catch (IOException ex) {
      throw new BeanDefinitionStoreException(
            "IOException parsing XML document from " + encodedResource.getResource(), ex);
   }
   finally {
      currentResources.remove(encodedResource);
      if (currentResources.isEmpty()) {
         this.resourcesCurrentlyBeingLoaded.remove();
      }
   }
}
```

```java
protected int doLoadBeanDefinitions(InputSource inputSource, Resource resource)
      throws BeanDefinitionStoreException {

   try {
      Document doc = doLoadDocument(inputSource, resource); // 加载资源文件为Document
      int count = registerBeanDefinitions(doc, resource); // 根据获取的Document 去注册bean definition
      if (logger.isDebugEnabled()) {
         logger.debug("Loaded " + count + " bean definitions from " + resource);
      }
      return count;
   }
}
```

### 功能扩展prepareBeanFactory 

在进入prepareBeanFactory 之前，spring 以及完成了对配置的解析。 而ApplicaitionContext 在功能上的扩展也由此展开,主要对一下几个方面进行了扩展

- 增加对SpEL 语言的支持
- 增加对属性编辑器的支持
- 增加对一些内置类 ，比如EnvironmentAware 的信息注入
- 设置了依赖功能可忽略的接口
- 注册一些固定依赖的属性
- 增加AspectJ 的支持
- 将相关环境变量及属性注册以单例模式注册



```java
protected void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {
   // 设置beanFactory 的classLoader 为当前context的 classLoader
   beanFactory.setBeanClassLoader(getClassLoader());
   // 设置beanFactory 的表达式语言处理器
   beanFactory.setBeanExpressionResolver(new StandardBeanExpressionResolver(beanFactory.getBeanClassLoader()));
   // 为bean factory 增加一个默认的 property editor , 这个主要是对bean 的属性等设置管理的一个工具
   beanFactory.addPropertyEditorRegistrar(new ResourceEditorRegistrar(this, getEnvironment()));

   // 添加eanPostProcessor
   beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
   // 设置了几个忽略自动装配的接口
   beanFactory.ignoreDependencyInterface(EnvironmentAware.class);
   beanFactory.ignoreDependencyInterface(EmbeddedValueResolverAware.class);
   beanFactory.ignoreDependencyInterface(ResourceLoaderAware.class);
   beanFactory.ignoreDependencyInterface(ApplicationEventPublisherAware.class);
   beanFactory.ignoreDependencyInterface(MessageSourceAware.class);
   beanFactory.ignoreDependencyInterface(ApplicationContextAware.class);

   // 设置几个自动装配的特殊规则
   beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
   beanFactory.registerResolvableDependency(ResourceLoader.class, this);
   beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
   beanFactory.registerResolvableDependency(ApplicationContext.class, this);

   beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(this));

   // 增加对AspectJ  的支持
   if (beanFactory.containsBean(LOAD_TIME_WEAVER_BEAN_NAME)) {
      beanFactory.addBeanPostProcessor(new LoadTimeWeaverAwareProcessor(beanFactory));
      beanFactory.setTempClassLoader(new ContextTypeMatchClassLoader(beanFactory.getBeanClassLoader()));
   }
   if (!beanFactory.containsLocalBean(ENVIRONMENT_BEAN_NAME)) {
      beanFactory.registerSingleton(ENVIRONMENT_BEAN_NAME, getEnvironment());
   }
   if (!beanFactory.containsLocalBean(SYSTEM_PROPERTIES_BEAN_NAME)) {
      beanFactory.registerSingleton(SYSTEM_PROPERTIES_BEAN_NAME, getEnvironment().getSystemProperties());
   }
   if (!beanFactory.containsLocalBean(SYSTEM_ENVIRONMENT_BEAN_NAME)) {
      beanFactory.registerSingleton(SYSTEM_ENVIRONMENT_BEAN_NAME, getEnvironment().getSystemEnvironment());
   }
}
```

### 执行BeanFactoryPostProcessor的方法 invokeBeanFactoryPostProcessors(beanFactory)

BeanFactoryPostProcessor 接口和BeanPostProcessor 类似，可以对bean 的定义进行处理。也就是说，spring ioc  容器允许BeanFactoryPostProcessor 在容器实际实例化任何其他bean 之前读取配置元数据 ，并有可能修改他。 同时可以配置多个BeanFactoryPostProcessor 。 通过设置order 属性来控制BeanFactoryPostProcessor 的执行顺序 （需要实现Ordered 接口） 

### 注册BeanPostProcessor 

这里只是注册BeanFactoryPostProcessor ,真正的调用是在Bean 的实例化阶段调用的。  该功能BeanFactory 是不支持的，需要手动添加，而 ApplicationContext 则是自动注册的。 自动注册就是在这里注册的

```java
public static void registerBeanPostProcessors(
      ConfigurableListableBeanFactory beanFactory, AbstractApplicationContext applicationContext) {

   String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);

   // Register BeanPostProcessorChecker that logs an info message when
   // a bean is created during BeanPostProcessor instantiation, i.e. when
   // a bean is not eligible for getting processed by all BeanPostProcessors.
   int beanProcessorTargetCount = beanFactory.getBeanPostProcessorCount() + 1 + postProcessorNames.length;
   beanFactory.addBeanPostProcessor(new BeanPostProcessorChecker(beanFactory, beanProcessorTargetCount));

   // Separate between BeanPostProcessors that implement PriorityOrdered,
   // Ordered, and the rest.
   // 使用priorityOrdered 保证顺序
   List<BeanPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
   List<BeanPostProcessor> internalPostProcessors = new ArrayList<>();
   // 保证ordered顺序
   List<String> orderedPostProcessorNames = new ArrayList<>();
   // 无需的BeanPostProcessor
   List<String> nonOrderedPostProcessorNames = new ArrayList<>();
   for (String ppName : postProcessorNames) {
      if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
         // 根据BeanName  获取需要注册的BeanPostProcessor
         BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
         priorityOrderedPostProcessors.add(pp);
         if (pp instanceof MergedBeanDefinitionPostProcessor) {
            internalPostProcessors.add(pp);
         }
      }
      else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
         orderedPostProcessorNames.add(ppName);
      }
      else {
         nonOrderedPostProcessorNames.add(ppName);
      }
   }

   // First, register the BeanPostProcessors that implement PriorityOrdered.
   // 注册所有实现了priorityOrdered 的BeanPostProcessors
   sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
   registerBeanPostProcessors(beanFactory, priorityOrderedPostProcessors);

   // Next, register the BeanPostProcessors that implement Ordered.
   // 注册所有实现了Ordered  的BeanPostProcessors
   List<BeanPostProcessor> orderedPostProcessors = new ArrayList<>(orderedPostProcessorNames.size());
   for (String ppName : orderedPostProcessorNames) {
      BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
      orderedPostProcessors.add(pp);
      if (pp instanceof MergedBeanDefinitionPostProcessor) {
         internalPostProcessors.add(pp);
      }
   }
   sortPostProcessors(orderedPostProcessors, beanFactory);
   registerBeanPostProcessors(beanFactory, orderedPostProcessors);

   // Now, register all regular BeanPostProcessors.
   // 注册所有无序的BeanPostProcessors
   List<BeanPostProcessor> nonOrderedPostProcessors = new ArrayList<>(nonOrderedPostProcessorNames.size());
   for (String ppName : nonOrderedPostProcessorNames) {
      BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
      nonOrderedPostProcessors.add(pp);
      if (pp instanceof MergedBeanDefinitionPostProcessor) {
         internalPostProcessors.add(pp);
      }
   }
   registerBeanPostProcessors(beanFactory, nonOrderedPostProcessors);

   // Finally, re-register all internal BeanPostProcessors.
   // 最后 ，注册所有MergedBeanDefinitionPostProcessor 类型的BeanPostProcessor
   sortPostProcessors(internalPostProcessors, beanFactory);
   registerBeanPostProcessors(beanFactory, internalPostProcessors);

   // Re-register post-processor for detecting inner beans as ApplicationListeners,
   // moving it to the end of the processor chain (for picking up proxies etc).
   // 添加ApplicationListener 探测器
   beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(applicationContext));
}
```

### 初始化国际化资源initMessageSource



### 初始化事件监听器initApplicationEventMulticaster

```java
protected void initApplicationEventMulticaster() {
   ConfigurableListableBeanFactory beanFactory = getBeanFactory();
   // 如果用户自定义了事件广播器, 那么使用用户自定义的事件广播器
   if (beanFactory.containsLocalBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME)) {
      this.applicationEventMulticaster =
            beanFactory.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
      if (logger.isTraceEnabled()) {
         logger.trace("Using ApplicationEventMulticaster [" + this.applicationEventMulticaster + "]");
      }
   }
   else {
      // 没有自定义,则使用默认的SimpleApplicationEventMulticaster
      this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
      beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, this.applicationEventMulticaster);
      if (logger.isTraceEnabled()) {
         logger.trace("No '" + APPLICATION_EVENT_MULTICASTER_BEAN_NAME + "' bean, using " +
               "[" + this.applicationEventMulticaster.getClass().getSimpleName() + "]");
      }
   }
}
```



### 初始化非延迟加载单例  finishBeanFactoryInitialization

-  ConversionService 的设置
- 冻结所有的bean 定义
- 初始化剩下的单实例（非惰性）

```java
protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
   // Initialize conversion service for this context.
   if (beanFactory.containsBean(CONVERSION_SERVICE_BEAN_NAME) &&
         beanFactory.isTypeMatch(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class)) {
      beanFactory.setConversionService( // 注册转换器
            beanFactory.getBean(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class));
   }

   if (!beanFactory.hasEmbeddedValueResolver()) {
      beanFactory.addEmbeddedValueResolver(strVal -> getEnvironment().resolvePlaceholders(strVal));
   }

   String[] weaverAwareNames = beanFactory.getBeanNamesForType(LoadTimeWeaverAware.class, false, false);
   for (String weaverAwareName : weaverAwareNames) {
      getBean(weaverAwareName);
   }

   beanFactory.setTempClassLoader(null);

   // 冻结所有的bean 定义， 注册的bean 的定义将不被修改或进一步处理
   beanFactory.freezeConfiguration();

   // 初始化剩下的单实例
   beanFactory.preInstantiateSingletons();
}
```

#### 初始化剩下的非懒加载实例

```java
public void preInstantiateSingletons() throws BeansException {
   if (logger.isTraceEnabled()) {
      logger.trace("Pre-instantiating singletons in " + this);
   }

   // Iterate over a copy to allow for init methods which in turn register new bean definitions.
   // While this may not be part of the regular factory bootstrap, it does otherwise work fine.
   List<String> beanNames = new ArrayList<>(this.beanDefinitionNames); // 获取所有的beanDefinition name

   // Trigger initialization of all non-lazy singleton beans... 所有非懒加载的bean 的初始化
   for (String beanName : beanNames) { // 遍历实例化bean
      RootBeanDefinition bd = getMergedLocalBeanDefinition(beanName);
      if (!bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit()) {
         if (isFactoryBean(beanName)) { // 如果bean 是Factory Bean
            Object bean = getBean(FACTORY_BEAN_PREFIX + beanName);
            if (bean instanceof FactoryBean) {
               FactoryBean<?> factory = (FactoryBean<?>) bean;
               boolean isEagerInit;
               if (System.getSecurityManager() != null && factory instanceof SmartFactoryBean) {
                  isEagerInit = AccessController.doPrivileged(
                        (PrivilegedAction<Boolean>) ((SmartFactoryBean<?>) factory)::isEagerInit,
                        getAccessControlContext());
               }
               else {
                  isEagerInit = (factory instanceof SmartFactoryBean &&
                        ((SmartFactoryBean<?>) factory).isEagerInit());
               }
               if (isEagerInit) {
                  getBean(beanName);
               }
            }
         }
         else {
            getBean(beanName);
         }
      }
   }

   // Trigger post-initialization callback for all applicable beans...
   for (String beanName : beanNames) {
      Object singletonInstance = getSingleton(beanName);
      if (singletonInstance instanceof SmartInitializingSingleton) {
         SmartInitializingSingleton smartSingleton = (SmartInitializingSingleton) singletonInstance;
         if (System.getSecurityManager() != null) {
            AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
               smartSingleton.afterSingletonsInstantiated();
               return null;
            }, getAccessControlContext());
         }
         else {
            smartSingleton.afterSingletonsInstantiated();
         }
      }
   }
}
```

### finishRefresh

#### Lifecycle 接口

Lifecycle 包含start stop  ，实现了此接口后，spring 会保证在启动的时候调用其start 方法开始生命周期,并在spring 关闭的时候调用stop 方法来结束生命周期,通常用来配置后台程序



### 循环依赖

​	使用三级缓存解决（实例化和初始化分开处理，提前暴露对象）  ，  set 方式可以解决循环依赖（set 先创建对象，再进行赋值） ，而构造方法是没有办法解决循环依赖问题的

