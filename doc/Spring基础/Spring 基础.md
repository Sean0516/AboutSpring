### spring 框架的四大原则
1. 使用pojo 进行轻量级和最小侵入式开发
2. 使用依赖注入和基于接口编程实现耦合 (所谓依赖注入，就是指是容器来负责创建对河和维护对象间的依赖关系，而不是通过对象本身负责自己的创建和解决字节的依赖，依赖注入的目的主要是为了解耦，体现了一种组合的理论)
3. 通过AOP 和默认习惯进行声明式编程
4. 使用AOP 和模板减少模式化代码

spring IOC 容器 （applicationcontext）负责创建bean，并通过容器将功能类bean注入到需要的bean中，spring 提供xml ,注解， Java配置， groovy配置实现bean的创建和注入

### 声明bean的注解：
1. @Component  通用的注解，可标注任意类为Spring 组件，如果一个Bean 不知道属于那个层，可以使用该注解
2. @Service 在业务逻辑（服务层）使用 主要涉及一些复杂的逻辑 
3. @Repository  在数据访问成（dao层 ）使用 ，主要用于数据库相关操作
4. @Controller  在视图层使用 （springMVC） 主要接收用户请求并调用service 层返回数据给前端页

### 编写功能类的bean  流程
1. 使用@service注解声明当前类是spring管理的一个bean
2. 使用autowired 将当前的实体bean注入到service中

### 编写配置类的流程
1. 使用@Configuration 声明当前类是一个配置类

2. 使用@ComponentScan ，自动扫描报名下所有使用 @Service @Component @Repository @Controller 的类 并注册为bean
除了使用ComponentScan 注入bean 还可以通过 AnnotationConfigApplicationContext 作为spring容器，接受一个配置类作为参数，并返回配置类的并，通过返回的并来对配置类做操作
