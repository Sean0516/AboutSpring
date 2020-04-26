在基于spring的应用中，应用的对象生存于spring容器中，容器负责创建和装配对象， 配置他们并且管理他们的整个生命周期。容器是spring 框架的核心，spring容器使用DI （依赖注入）管理构成应用的组建，他会创建相互协作的组建之间的关联 

spring 容器可以分为两种不同的类型 bean工厂，以及应用上下文。 其中 bean工厂是最简单的容器，提供基本的DI 支持，而应用上下文则是基于BeanFactory 构建，并提供应用框架级别的服务

spring 自带多种类型的应用上下文
1. AnnotationConfigApplicationContext ：从一个或多个基于Java的配置类中加载spring应用上下文
2. AnnotationConfigWebApplicationContext :从一个或多个基于Java 的配置类中价值spring web 应用上下文
3. ClassPathXmlApplicationContext 从类路径下的一个或多个xml 配置文件中加载上下文定义，把应用上下文的定义文件作为类资源
4. FileSystemXmlApplicationContext 从文件系统的xml 配置文件中加载上下文定义
5. XmlWebApplicationContext 从web 应用下的xml 配置文件中加载上下文定义

当应用上下文准备就绪后，我们可以调用上下文的getBaen（）方法从容器中获取bean

