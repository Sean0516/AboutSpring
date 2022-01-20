```java
public class XmlBeanFactory extends DefaultListableBeanFactory {

   private final XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(this);// xml bean definition reader

   public XmlBeanFactory(Resource resource) throws BeansException {
      this(resource, null);
   }


   public XmlBeanFactory(Resource resource, BeanFactory parentBeanFactory) throws BeansException { // load file
      super(parentBeanFactory);
      this.reader.loadBeanDefinitions(resource);
   }

}
```

![image-20220120113502129](https://gitee.com/Sean0516/image/raw/master/img/image-20220120113502129.png)

从XmlBeanFactory 的代码中可以看到，bean 的初始化，就两步 

1. 通过ClassPathResource加载资源
2. 使用XmlBeanDefinitionReader 加载bean

### 配置文件封装

spring 的配置文件通过ClassPathResource 进行封装

![image-20220120105042688](https://gitee.com/Sean0516/image/raw/master/img/image-20220120105042688.png)

### 加载Bean

下面是加载bean 的时序图

![image-20220120114303911](https://gitee.com/Sean0516/image/raw/master/img/image-20220120114303911.png)

#### 首先调用loadBeanDefinitions 

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

#### doLoadBeanDefinitions 进行实际的操作 实际操作都是调用其他类的方法

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

#### doLoadDocument 调用DefaultDocumentLoader 中的 loadDocument 方法， 同时会创建DocumentBuilderFactory 和DocumentBuilder ，通过DocumentBuilder的parse 方法  builder.parse(inputSource) 将 资源文件转为document 对象

![image-20220120184846877](https://gitee.com/Sean0516/image/raw/master/img/image-20220120184846877.png)

```java
public Document loadDocument(InputSource inputSource, EntityResolver entityResolver,
      ErrorHandler errorHandler, int validationMode, boolean namespaceAware) throws Exception {

   DocumentBuilderFactory factory = createDocumentBuilderFactory(validationMode, namespaceAware);// 创建DocumentBuilderFactory
   if (logger.isTraceEnabled()) {
      logger.trace("Using JAXP provider [" + factory.getClass().getName() + "]");
   }
   DocumentBuilder builder = createDocumentBuilder(factory, entityResolver, errorHandler); // 创建DocumentBuilder
   return builder.parse(inputSource); // 解析资源文件，并使用DocumentBuilder parse 为document
}
```



#### XmlBeanDefinitionReader 根据返回的document ，调用registerBeanDefinitions(Document doc, Resource resource)  方法,进行bean definition 的注册

![image-20220120185128471](https://gitee.com/Sean0516/image/raw/master/img/image-20220120185128471.png)

1. 首先创建BeanDefinitionDocumentReader 同时，通过createReaderContext 方法，将资源文件转为XmlReaderContext.
2. 然后通过BeanDefinitionDocumentReader  的registerBeanDefinitions 方法，使用 Document 和XmlReaderContext.进行bean definition 的注册
3. 使用parseBeanDefinitions 解析并注册 BeanDefinition

```java
public int registerBeanDefinitions(Document doc, Resource resource) throws BeanDefinitionStoreException {
   BeanDefinitionDocumentReader documentReader = createBeanDefinitionDocumentReader(); //创建BeanDefinitionDocumentReader
   int countBefore = getRegistry().getBeanDefinitionCount();// 记录统计前tBeanDefinition 的加载个数
   documentReader.registerBeanDefinitions(doc, createReaderContext(resource));// 加载及注册bean
   return getRegistry().getBeanDefinitionCount() - countBefore;
}
protected void doRegisterBeanDefinitions(Element root) { // xml 解析
		// 解析前处理,留给子类实现
		preProcessXml(root);
		// 解析并注册bean definition
		parseBeanDefinitions(root, this.delegate);
		//解析后处理,留给子类实现
		postProcessXml(root);

		this.delegate = parent;
	}
```

```java
protected void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) {// bean 的解析及注册
   if (delegate.isDefaultNamespace(root)) {
      NodeList nl = root.getChildNodes();
      for (int i = 0; i < nl.getLength(); i++) {
         Node node = nl.item(i);
         if (node instanceof Element) {
            Element ele = (Element) node;
            if (delegate.isDefaultNamespace(ele)) {
               parseDefaultElement(ele, delegate);
            }
            else {
               delegate.parseCustomElement(ele);
            }
         }
      }
   }
   else {
      delegate.parseCustomElement(root);
   }
}
```

```java
private void parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate) {
   // 对import 标签处理
   if (delegate.nodeNameEquals(ele, IMPORT_ELEMENT)) {
      importBeanDefinitionResource(ele);
   }
   // 对alias  标签处理
   else if (delegate.nodeNameEquals(ele, ALIAS_ELEMENT)) {
      processAliasRegistration(ele);
   }
   // 对bean 标签的处理
   else if (delegate.nodeNameEquals(ele, BEAN_ELEMENT)) {
      processBeanDefinition(ele, delegate);
   }
   //  对beans 标签的处理
   else if (delegate.nodeNameEquals(ele, NESTED_BEANS_ELEMENT)) {
      // recurse
      doRegisterBeanDefinitions(ele);
   }
}
```

#### 注册的方法，实际上是通过DefaultBeanDefinitionDocumentReader 的registerBeanDefinitions 进行的，具体实现由doRegisterBeanDefinitions 实现

```java
protected void processBeanDefinition(Element ele, BeanDefinitionParserDelegate delegate) {
   // 使用BeanDefinitionParserDelegate 类的parseBeanDefinitionElement 方法进行元素解析，返回BeanDefinitionHolder
   BeanDefinitionHolder bdHolder = delegate.parseBeanDefinitionElement(ele);
   if (bdHolder != null) {
      bdHolder = delegate.decorateBeanDefinitionIfRequired(ele, bdHolder);
      try {
         // 对解析后的 dbHolder 进行注册
         BeanDefinitionReaderUtils.registerBeanDefinition(bdHolder, getReaderContext().getRegistry());
      }
      catch (BeanDefinitionStoreException ex) {
         getReaderContext().error("Failed to register bean definition with name '" +
               bdHolder.getBeanName() + "'", ele, ex);
      }
      // Send registration event. 发出响应事件, 通知相关的监听器, 表示这个bean 已经加载完成了
      getReaderContext().fireComponentRegistered(new BeanComponentDefinition(bdHolder));
   }
}
```



