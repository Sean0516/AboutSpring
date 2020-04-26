在默认情况下 spring应用上下文所有的bean都是作为以单例的形式创建的，也就是说，不管给定的bean 被注入到其他的bean 中多少次，每次所注入的都是同一个实例 

scope 描述的是spring 容器如何新建bean 的实例 。 spring 定义了多种作用域 ， 可以基于这些作用域使用@Scope 注解创建bean

Bean 的作用域有以下一种
1. singleton: 表示一个spring 容器只有一个bean 的实例 ，是spring的默认配置，全容器共享一个实例
2. prototype: 每次调用创建一个bean的实例(无论是通过其他bean 定义还是通过getBean() 方法) 都会创建一个新的bean 实例 类似Java 中的new操作符
3. request：针对一个特定的HTTP 会话使用相同的bean 实例，不同的HTTP会话会创建新的bean 实例 ， 只适合web 项目
4. session:  在web 项目中，每一个http session 新建一个bean 实例
5. global-session： 全局session 作用域，仅仅在基于portlet 的web 应用中采用意义。spring5 已经不再使用

示例代码：

```
    @Bean
    @Scope("singleton ")
    public  User user() {
     
        return new User;
    }
```
