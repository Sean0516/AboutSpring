

spring aop 的初始化，主要由 bean 初始化的 applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName) 方法实现。 当时实际上则是BeanPostProcessor 执行postProcessAfterInitialization方法



### 创建代理

```java
@Override
public Object postProcessAfterInitialization(@Nullable Object bean, String beanName) {
   if (bean != null) {
      // 根据给定的bean 的class 和 name  构建一个key
      Object cacheKey = getCacheKey(bean.getClass(), beanName);
      if (this.earlyProxyReferences.remove(cacheKey) != bean) {
         // 如果当前bean 适合被代理, 则需要封装指定bean
         return wrapIfNecessary(bean, beanName, cacheKey);
      }
   }
   return bean;
}
```

#### 创建代理的两个步骤

1. 获取增强方法或者增强器
2. 根据获取的增强进行代理

![image-20220124152843275](https://gitee.com/Sean0516/image/raw/master/img/image-20220124152843275.png)

```java
protected Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
   // 如果已经处理过了，则返回
   if (StringUtils.hasLength(beanName) && this.targetSourcedBeans.contains(beanName)) {
      return bean;
   }
   if (Boolean.FALSE.equals(this.advisedBeans.get(cacheKey))) {
      return bean;
   }
   // 判断给定的类是否代表一个基础设施类, 基础设施类不应该代理,或者配置了指定的bean不需要自动代理
   if (isInfrastructureClass(bean.getClass()) || shouldSkip(bean.getClass(), beanName)) {
      this.advisedBeans.put(cacheKey, Boolean.FALSE);
      return bean;
   }

   // 如果存在增强方法则创建代理
   Object[] specificInterceptors = getAdvicesAndAdvisorsForBean(bean.getClass(), beanName, null);
   // 如果获取到了增强则需要针对增强创建代理
   if (specificInterceptors != DO_NOT_PROXY) {
      this.advisedBeans.put(cacheKey, Boolean.TRUE);
      // 创建代理
      Object proxy = createProxy(
            bean.getClass(), beanName, specificInterceptors, new SingletonTargetSource(bean));
      this.proxyTypes.put(cacheKey, proxy.getClass());
      return proxy;
   }

   this.advisedBeans.put(cacheKey, Boolean.FALSE);
   return bean;
}
```



#### 获取增强方法

获取指定bean  的增强方法包含两个步骤 

1. 获取所有的增强器  findCandidateAdvisors
2. 寻找所有增强中适用于bean 的增强并应用  findAdvisorsThatCanApply

如果无法找到对应的增强器便返回  DO_NOT_PROXY

![image-20220124153457942](https://gitee.com/Sean0516/image/raw/master/img/image-20220124153457942.png)



##### 获取增强器

findCandidateAdvisors 的实现由AnnotationAwareAspectJAutoProxyCreator 类实现完成的..  

```java
@Override
protected List<Advisor> findCandidateAdvisors() {
   // Add all the Spring advisors found according to superclass rules.
   // 调用父类方法加载配置文件中的AOP 声明
   List<Advisor> advisors = super.findCandidateAdvisors();
   // Build Advisors for all AspectJ aspects in the bean factory.
   // 获取当前bean 的 注解增强功能
   if (this.aspectJAdvisorsBuilder != null) {
      advisors.addAll(this.aspectJAdvisorsBuilder.buildAspectJAdvisors());
   }
   return advisors;
}
public List<Advisor> buildAspectJAdvisors() {
		List<String> aspectNames = this.aspectBeanNames;

		if (aspectNames == null) {
			synchronized (this) {
				aspectNames = this.aspectBeanNames;
				if (aspectNames == null) {
					List<Advisor> advisors = new ArrayList<>();
					aspectNames = new ArrayList<>();
					// 获取所有的beanName
					String[] beanNames = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(
							this.beanFactory, Object.class, true, false);
					// 遍历所有的bean name 找出对应的增强方法
					for (String beanName : beanNames) {
						// 略过不合法的bean
						if (!isEligibleBean(beanName)) {
							continue;
						}
						// We must be careful not to instantiate beans eagerly as in this case they
						// would be cached by the Spring container but would not have been weaved.
						// 获取对应的bean 的类型
						Class<?> beanType = this.beanFactory.getType(beanName, false);
						if (beanType == null) {
							continue;
						}
						// 如果存在aspect 注解
						if (this.advisorFactory.isAspect(beanType)) {
							aspectNames.add(beanName);
							AspectMetadata amd = new AspectMetadata(beanType, beanName);
							if (amd.getAjType().getPerClause().getKind() == PerClauseKind.SINGLETON) {
								MetadataAwareAspectInstanceFactory factory =
										new BeanFactoryAspectInstanceFactory(this.beanFactory, beanName);
								// 解析标记AspectJ 注解中的增强方法
								List<Advisor> classAdvisors = this.advisorFactory.getAdvisors(factory);
								if (this.beanFactory.isSingleton(beanName)) {
									this.advisorsCache.put(beanName, classAdvisors);
								}
								else {
									this.aspectFactoryCache.put(beanName, factory);
								}
								advisors.addAll(classAdvisors);
							}
							else {
								// Per target or per this.
								if (this.beanFactory.isSingleton(beanName)) {
									throw new IllegalArgumentException("Bean with name '" + beanName +
											"' is a singleton, but aspect instantiation model is not singleton");
								}
								MetadataAwareAspectInstanceFactory factory =
										new PrototypeAspectInstanceFactory(this.beanFactory, beanName);
								this.aspectFactoryCache.put(beanName, factory);
								advisors.addAll(this.advisorFactory.getAdvisors(factory));
							}
						}
					}
					this.aspectBeanNames = aspectNames;
					return advisors;
				}
			}
		}

		if (aspectNames.isEmpty()) {
			return Collections.emptyList();
		}
		// 将增强器记录到缓存中
		List<Advisor> advisors = new ArrayList<>();
		for (String aspectName : aspectNames) {
			List<Advisor> cachedAdvisors = this.advisorsCache.get(aspectName);
			if (cachedAdvisors != null) {
				advisors.addAll(cachedAdvisors);
			}
			else {
				MetadataAwareAspectInstanceFactory factory = this.aspectFactoryCache.get(aspectName);
				advisors.addAll(this.advisorFactory.getAdvisors(factory));
			}
		}
		return advisors;
	}
```

##### 增强器的获取方法 getAdvisors

```java
public List<Advisor> getAdvisors(MetadataAwareAspectInstanceFactory aspectInstanceFactory) {
   // 获取标记为AspectJ 的类型
   Class<?> aspectClass = aspectInstanceFactory.getAspectMetadata().getAspectClass();
   // 获取标记为AspectJ 的name
   String aspectName = aspectInstanceFactory.getAspectMetadata().getAspectName();
   // 验证
   validate(aspectClass);

   MetadataAwareAspectInstanceFactory lazySingletonAspectInstanceFactory =
         new LazySingletonAspectInstanceFactoryDecorator(aspectInstanceFactory);

   List<Advisor> advisors = new ArrayList<>();
   for (Method method : getAdvisorMethods(aspectClass)) {
      Advisor advisor = getAdvisor(method, lazySingletonAspectInstanceFactory, 0, aspectName);
      if (advisor != null) {
         advisors.add(advisor);
      }
   }

   // If it's a per target aspect, emit the dummy instantiating aspect.
   if (!advisors.isEmpty() && lazySingletonAspectInstanceFactory.getAspectMetadata().isLazilyInstantiated()) {
      // 如果寻找的增强器不为空，而且又配置了增强延迟初始化，那么需要在首位加入同步实例化增强器
      Advisor instantiationAdvisor = new SyntheticInstantiationAdvisor(lazySingletonAspectInstanceFactory);
      advisors.add(0, instantiationAdvisor);
   }

   // Find introduction fields. 获取DeclaredParent 注解
   for (Field field : aspectClass.getDeclaredFields()) {
      Advisor advisor = getDeclareParentsAdvisor(field);
      if (advisor != null) {
         advisors.add(advisor);
      }
   }

   return advisors;
}
```



##### 寻找匹配的增强器



#### 创建代理

1. 获取当前类的属性
2. 添加代理接口
3. 封装advisor 增强器，并加入到 ProxyFacotry 中
4. 设置要代理的类
5. 自定义ProxyFactory 
6. 进行代理操作

```java
protected Object createProxy(Class<?> beanClass, @Nullable String beanName,
      @Nullable Object[] specificInterceptors, TargetSource targetSource) {

   if (this.beanFactory instanceof ConfigurableListableBeanFactory) {
      AutoProxyUtils.exposeTargetClass((ConfigurableListableBeanFactory) this.beanFactory, beanName, beanClass);
   }

   ProxyFactory proxyFactory = new ProxyFactory();
   // 获取当前类中相关属性
   proxyFactory.copyFrom(this);
   // 检测proxy target class 设置以及 preserve target class 属性
   if (proxyFactory.isProxyTargetClass()) {
      // Explicit handling of JDK proxy targets (for introduction advice scenarios)
      if (Proxy.isProxyClass(beanClass)) {
         // Must allow for introductions; can't just set interfaces to the proxy's interfaces only.
         for (Class<?> ifc : beanClass.getInterfaces()) {
            // 添加代理接口
            proxyFactory.addInterface(ifc);
         }
      }
   }
   else {
      // No proxyTargetClass flag enforced, let's apply our default checks...
      if (shouldProxyTargetClass(beanClass, beanName)) {
         proxyFactory.setProxyTargetClass(true);
      }
      else {
         evaluateProxyInterfaces(beanClass, proxyFactory);
      }
   }
   // 获取增强器
   Advisor[] advisors = buildAdvisors(beanName, specificInterceptors);
   // 加入增强器
   proxyFactory.addAdvisors(advisors);
   // 设置要代理的类
   proxyFactory.setTargetSource(targetSource);
   // 定制代理
   customizeProxyFactory(proxyFactory);
   // 用来控制代理工厂被配置后，是否还允许修改通知
   proxyFactory.setFrozen(this.freezeProxy);
   if (advisorsPreFiltered()) {
      proxyFactory.setPreFiltered(true);
   }

   return proxyFactory.getProxy(getProxyClassLoader());
}
```

##### 加入增强器以及设置需要代理的类



##### 具体代理的创建

```java
@Override
public AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException {
   if (config.isOptimize() || config.isProxyTargetClass() || hasNoUserSuppliedProxyInterfaces(config)) {
      Class<?> targetClass = config.getTargetClass();
      if (targetClass == null) {
         throw new AopConfigException("TargetSource cannot determine target class: " +
               "Either an interface or a target is required for proxy creation.");
      }
      if (targetClass.isInterface() || Proxy.isProxyClass(targetClass)) {
         return new JdkDynamicAopProxy(config);
      }
      return new ObjenesisCglibAopProxy(config);
   }
   else {
      return new JdkDynamicAopProxy(config);
   }
}
```

对于使用 jdk 还是cglib 实现动态代理，有一下总结

- 如果对象目标实现了接口，默认情况下会采用jdk 的动态代理实现AOP
- 如果目标对象实现了接口，可以强制使用Cglib 实现AOP
- 如果目标对象没有实现接口，必须采用Cglib 来实现 AOP

##### jdk 和Cglib 字节码生成的区别

-  JDK 动态代理只针对实现了接口的类生成代理，不能针对类
- Cglib 是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法。 因为是继承，所以该类或方法最好不要声明成final 

##### 获取代理

实现代理的方法大同小异，都是首先构造拦截链，然后封装此链进行串联调用。



