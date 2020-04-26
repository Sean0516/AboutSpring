传统Java应用中，bean 的声明周期很简单，使用new 关键字进行bean 的实例化，然后就可以使用bean了。当不使用了，则由Java的垃圾回收机制回收 ，而spring的bean 有属于自己的过程

### spring bean 的生命周期
1. spring 对bean 进行实例化
2. spring 将值和bean 的引用注入到bean 对于的属性中
3. 如果bean 实现了 BeanNameAware 接口，则将bean 的id 传递给setBeanName 方法
4. 如果实现了BeanFactoryAware 接口 ，调用setBeanFactory 方法，将beanFactory 容器实力传入
5. 如果实现了ApplicationContextAware 接口， 将调用setApplicationContext  方法，将 bean 所在的应用上下文引用传入进来
6. 如果bean 实现了 beanPostProcessor 接口
7. 如果实现了 InitializingBean接口  ，执行 afterProperiesSet方法
8. 如果有加载这个Bean 的容器相关的beanPostProcessor对象，执行 postProcessAfterInitialization() 方法
9. 经过上面的准备，bean 已经准备就绪了，可以被应用程序使用，他们会一直驻留在应用上下文中，直到应用上下文被销毁
10. 如果bean 实现了DisposableBean 接口，spring将调用它的destroy 方法
11. 当要销毁 Bean 的时候，如果 Bean 在配置文件中的定义包含 destroy-method 属性，执行指定的方法。