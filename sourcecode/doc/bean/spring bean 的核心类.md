### DefaultListableBeanFactory

XmlBeanFactory 继承自 DefaultListableBeanFactory ，而DefaultListableBeanFactory 是整个bean 加载的核心部分。 是spring 注册及加载Bean 的默认实现 。而对于XmlBeanFactory 与DefaultListableBeanFactory 之间不同的地方在于XmlBeanFactory 中使用了自定义的xml 读取器 XmlBeanDefinitionReader  ，实现了个性化的 BeanDefinitionReader 读取 。

![image-20220119144516546](https://gitee.com/Sean0516/image/raw/master/img/image-20220119144516546.png)

#### AliasRegistery  接口

定义对alias 的简单增删改操作

```java
/**
 * 注册
 */
void registerAlias(String name, String alias);

/**
 * 删除
 */
void removeAlias(String alias);

/**
 * Determine whether the given name is defined as an alias
 * (as opposed to the name of an actually registered component).
 * @param name the name to check
 * @return whether the given name is an alias
 */
boolean isAlias(String name);

/**
 * 获取alias 
 */
String[] getAliases(String name);
```

#### SimpleAliasRegistry  

使用map 作为alias 的缓存，并且对AliasRegistery 接口进行实现

```java
private final Map<String, String> aliasMap = new ConcurrentHashMap<>(16);
// 注册
public void registerAlias(String name, String alias) {
		Assert.hasText(name, "'name' must not be empty");
		Assert.hasText(alias, "'alias' must not be empty");
		synchronized (this.aliasMap) {
			if (alias.equals(name)) {
				this.aliasMap.remove(alias);
				if (logger.isDebugEnabled()) {
					logger.debug("Alias definition '" + alias + "' ignored since it points to same name");
				}
			}
			else {
				String registeredName = this.aliasMap.get(alias);
				if (registeredName != null) {
					if (registeredName.equals(name)) {
						// An existing alias - no need to re-register
						return;
					}
					if (!allowAliasOverriding()) {
						throw new IllegalStateException("Cannot define alias '" + alias + "' for name '" +
								name + "': It is already registered for name '" + registeredName + "'.");
					}
					if (logger.isDebugEnabled()) {
						logger.debug("Overriding alias '" + alias + "' definition for registered name '" +
								registeredName + "' with new target name '" + name + "'");
					}
				}
				checkForAliasCircle(name, alias);
				this.aliasMap.put(alias, name);
				if (logger.isTraceEnabled()) {
					logger.trace("Alias definition '" + alias + "' registered for name '" + name + "'");
				}
			}
		}
	}
```

#### SingletonBeanRegistry 接口

定义对单例的注册及获取

```java
void registerSingleton(String beanName, Object singletonObject); // 根据指定的bean name 注册单例bean

@Nullable
Object getSingleton(String beanName); // 根据bean name 获取单例bean

boolean containsSingleton(String beanName); // 判断是否包含单例对象

String[] getSingletonNames();

int getSingletonCount();

Object getSingletonMutex(); 
```

#### DefaultSingletonBeanRegistry

 继承 SimpleAliasRegistry 实现SingletonBeanRegistry 接口。 对接口SingletonBeanRegistry 各个函数做具体的实现

```java
/** Cache of singleton objects: bean name to bean instance.
 * 一级缓存 spring 的容器,也就是存放完整的bean 实例, 已经实例化和初始化好的实例
 */
private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

/** Cache of singleton factories: bean name to ObjectFactory.
 *三级缓存  存放的是ObjectFactory ,传入的是一个匿名内部类，ObjectFactory.getObject 最终调用的是 getEarlyBeanReference 方法
 *如果bean 被代理  getEarlyBeanReference 方法 返回bean 的代理对象 如果bean 未被代理 ，则返回 原来bean 的实例
 */
private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);

/** Cache of early singleton objects: bean name to bean instance.
 *二级缓存， 主要用于保存未初始化的对象 ，属性未填充。 同时分为bean 是否可以被AOP切面代理 。如果没有，则保存属性未填充的半成品bean 实例。
 * 如果被AOP 代理，则保存的是代理的bean 实例 BeanProxy ，目标bean 还是半成品
 */
private final Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>(16);
```

#### FactoryBeanRegistrySupport

在DefaultSingletonBeanRegistry 的基础上，增加了对FactoryBean 的特殊处理功能

```java
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

   private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>(16); // 使用map 来报错FactoryBean

   @Nullable
   protected Class<?> getTypeForFactoryBean(FactoryBean<?> factoryBean) { // 根据指定的类型获取对应的FactoryBean
   }
   @Nullable
   protected Object getCachedObjectForFactoryBean(String beanName) {// 根据 bean name  获取FactoryBean
      return this.factoryBeanObjectCache.get(beanName);
   }

   protected Object getObjectFromFactoryBean(FactoryBean<?> factory, String beanName, boolean shouldPostProcess) {

   }

   /**
    * Obtain an object to expose from the given FactoryBean.
    * @param factory the FactoryBean instance
    * @param beanName the name of the bean
    * @return the object obtained from the FactoryBean
    * @throws BeanCreationException if FactoryBean object creation failed
    * @see org.springframework.beans.factory.FactoryBean#getObject()
    */
   private Object doGetObjectFromFactoryBean(FactoryBean<?> factory, String beanName) throws BeanCreationException { // 具体获取FactoryBean 的实现
   }
 }
```

#### AbstractBeanFactory

综合FactoryBeanRegistrySupport 和ConfigurableBeanFactory 的功能

#### BeanFactory 

定义各种获取Bean  及bean 的各种属性 

![image-20220119180007401](https://gitee.com/Sean0516/image/raw/master/img/image-20220119180007401.png)

#### HierarchicalBeanFactory

  继承BeanFactory ，再BeanFactory 定义的功能基础上，增加了对parentFactory 的支持

```java
public interface HierarchicalBeanFactory extends BeanFactory {

   @Nullable
   BeanFactory getParentBeanFactory(); // 获取父 bean factory
   boolean containsLocalBean(String name); //返回本地 bean 工厂是否包含给定名称的 bean

}
```

#### ConfigurableBeanFactory

继承 HierarchicalBeanFactory, SingletonBeanRegistry  ，提供配置Factory 的方法

例如 addBeanPostProcessor  setParentBeanFactory setBeanClassLoader 等方法

#### ListableBeanFactory 

根据各种条件获取Bean 的相关信息

![image-20220119182911658](https://gitee.com/Sean0516/image/raw/master/img/image-20220119182911658.png)

- 获取beandefinition数量
- 获取所有的的BeanDefinitionNames 

#### BeanDefinitionRegistry 

定义对BeanDefinition 的各种增删改操作 ,BeanDefinitionRegistry 的具体实现由 DefaultListableBeanFactory 来实现

使用 

```java
private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
```

来保存 BeanDefinition

```java
public interface BeanDefinitionRegistry extends AliasRegistry {

   void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) // 注册bean definition
         throws BeanDefinitionStoreException;
    
   void removeBeanDefinition(String beanName) throws NoSuchBeanDefinitionException; // 根据bean name 删除BeanDefinition

   BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException; // 根据bean name 获取BeanDefinition

   boolean containsBeanDefinition(String beanName);// 判断是否包含当前bean name 的BeanDefinition

   String[] getBeanDefinitionNames(); // 获取所有的BeanDefinition name
 
   int getBeanDefinitionCount(); // 返回BeanDefinition 的数量
 
   boolean isBeanNameInUse(String beanName); // 判断bean name是否被使用

}
```

#### AutowireCapableBeanFactory

提供创建Bean  ，自动注入， 初始化以及应用Bean 的后处理器

![image-20220120101515226](https://gitee.com/Sean0516/image/raw/master/img/image-20220120101515226.png)

#### AbstractAutowireCapableBeanFactory

综合AutowireCapableBeanFactory 并对接口 Autowire BeanFactory 进行实现

#### DefaultListableBeanFactory

综合上面的所有功能。 主要是对bean 注册后的处理

### XmlBeanFactory

XmlBeanFactory 对DefaultListableBeanFactory 进行了扩展

```java
public class XmlBeanFactory extends DefaultListableBeanFactory {

   private final XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(this); // xml bean definition reader
   public XmlBeanFactory(Resource resource) throws BeansException {
      this(resource, null);
   }
   public XmlBeanFactory(Resource resource, BeanFactory parentBeanFactory) throws BeansException { // load file
      super(parentBeanFactory);
      this.reader.loadBeanDefinitions(resource);
   }
}
```

### XmlBeanDefinitionReader

xml 配置文件的读取是spring 中重要的功能，因为spring 的大部分功能都是以配置作为切入点的。

![image-20220120102747444](https://gitee.com/Sean0516/image/raw/master/img/image-20220120102747444.png)

- ResourceLoader  定义资源加载器，主要应用于根据给定的资源文件地址返回对应的Resource
- BeanDefinitionReader 主要定义资源文件读取并转换为BeanDefinition 的各个功能
- EnvironmentCapable  定义获取Environment 的方法
- DocumentLoader  定义从资源文件加载到转换为Document 的功能
- AbstractBeanDefinitionReader  实现 EnvironmentCapable   和 BeanDefinitionReader ，并对定义的功能进行扩展和实现
- BeanDefinitionDocumentReader 定义读取Document  并注册BeanDefinition 功能

由此可知，xml 读取xml 注入到bean的基本流程如下

1. 通过继承自AbstractBeanDefinitionReader 中的方法，来使用ResourLoader 将资源文件路径转换为对应的Resource 文件
2. tonggDocumentLoader 对Resource 文件进行转换，将Resource 文件转为Document 文件
3. 对Docment 和 Element 进行解析 
4. 将解析后的数据注册为BeanDefinition

