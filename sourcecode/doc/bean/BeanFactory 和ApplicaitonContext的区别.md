BeanFactory 加载xml 后，只会对解析和注册 ，并不会对bean 进行加载，只有调用getBean 才会对bean 进行加载

而ApplicationContext 则会在resfresh 方法中，执行finishBeanFactoryInitialization(beanFactory); 加载所有的非懒加载的单例bean

同时ApplicationContext 对BeanFactory 的功能进行了扩展



