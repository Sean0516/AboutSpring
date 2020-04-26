### 环境与 profile
spring 支持不同环境下对不同bean 的装配 ，一般做法是在 bean 注解下 添加 profile注解来区分不同的环境时不同bean 的运行。 并且在应用部署在不同环境时，要确保对应的profile处于激活状态

### 自定义条件Bean
spring通过@Conditional 注解 根据特定的条件来实现bean 的创建

@Conditional 根据满足某一个特定条件创建一个特定的bean  ,例如，当某个jar 包在一个类的路径下，自动配置一个或多个bean 或者只有某个bean 被创建才会创建另外一个bean  总的来说， 就是通过特定的条件来控制bean 的创建行为。 

##### 实现步骤：
1. 首先 实现 condition 类 。重写 matchs 方法


```
public class LinuxCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return conditionContext.getEnvironment().getProperty("os.name").contains("Linux");
    }
}

public class WindowsCondition implements org.springframework.context.annotation.Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return conditionContext.getEnvironment().getProperty("os.name").contains("Windows");
    }
    
```

2. 通过 conditional 注解，符合对于的条件则实例化对于的bean

```
@Configuration
public class ConditionConfig {
    @Bean
    @Conditional(WindowsCondition.class)
    public IUser windowsUser(){
        return new WindowsUserImol();
    }
    @Bean
    @Conditional(LinuxCondition.class)
    public IUser linuxUser(){
        return new LinuxUserImpl();
    }
}
```
