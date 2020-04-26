Spring  中，对象不需要自己查找或创建与其所关联的其他对象， 容器会负责将需要相互协作的对象引用赋予各个对象 。创建应用对象之间协作关系的行为通常称为装配。 这也是依赖注入的本质。spring 装配的方式有三种

1. 隐式的bean 发现机制和自动装配
1. Java 中显式配置
1. XML 中显式配置

### 自动化装配 Bean 
spring 从组件扫描（spring自动发现应用上下文所创建的Bean ）和自动装配 （spring 自动满足 bean 之间的依赖）两个角度实现自动化装配

Spring 通过 @Component 注解 ，将类注解为组件类,所有带有@Component 的类都会被创建为Bean。 通过注解 @ComponentScan 开启组件扫描 (可以通过value  的设置组建扫描的基础包名) 。当然，对于spring boot 而言 ，默认开启了组建扫描 。在需要使用对象的地方，通过@Autowired 注解 将需要的bean 注入到类中，完成自动装配

所谓自动装配，就是让spring自动满足bean依赖的一种方法，在满足以来的过程中，会在spring应用上下文中需要某个bean需求的其他bean。为了声明将要进行自动装配，可以使用@Autowired 注解

### 通过Java代码装配bean
虽然通过组件扫描和自动装配实现spring自动化配置更方便，当时对于一些第三方库中的一些组件装配，无法使用组件扫描的方式，所以这个时候就需要使用显示装配了。一般推荐使用javaConfig 来实现，因为他更为强大，类型安全并且对重构友好。 同时，JavaConfig 与其他Java代码有所区别，他与应用程序中的业务逻辑和领域代码是不同的，JavaConfig 是配置代码，不包含任何业务逻辑，通常 javaConfig 应该放在单独的包中，使它与其他的应用程序逻辑代码分离开来。
### 通过Java代码实现bean 的装配实现
1. 创建配置类 在类上添加@Configration 注解，表示该类是一个配置类
2. 在配置类中声明@Bean 在@bean 下面声明一个方法，创建需要的实例。@Bean注解会告诉spring这个方法将会返回一个对象 ，并将该对象注册为spring 上下文中的Bean


```
   @Bean
    public  User user() {
        return new User;
    }
```

### XML 中显式配置
目前使用太少



