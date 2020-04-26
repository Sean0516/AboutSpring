![image](https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1587694634&di=57f31cb42a70ff3bf65701c502a8a86b&src=http://img.mp.itc.cn/upload/20170227/f58b173fb1394b4dbacadaf5d30105b4_th.jpeg)

图片来自百度百科

下面是spring 不同模块的作用

###  核心容器 Core Container
1. spring-core  核心工具类

core  和 beans 是框架的基础部分，提供ioc 和依赖注入特性
2. spring- beans spring定义bean的支持
3. spring-context 运行时spring容器
4. spring-context-support spring 容器对第三方包的集成支持
5. spring-expression 提供一个强大的表达式语言，使用表达式语言在运行时查询和操作对象
6. spring-aop  spring-aspects  基于代理的aop支持 基于aspectJ的aop 支持 。

### Messaging消息
1. spring-messaging  对消息架构和协议的支持

### web
1. spring-web 提供基础的web集成功能，在web项目中提供spring容器
2. spring-webmvc 提供基于servlet的springmvc使得模型范围内的代码合weborms之间能够清楚地分离开来，并于spring 框架的其他特性继承在一起
3. spring-websocket  提供websocket 服务 （分为 snmop 与自定义socket 两种）
4. spring- webmvc-portket 提供portlet 环境支持

### Data Access 数据访问集成
1. spring-jdbc 提供jdbc 访问数据库的支持 ，该模块提供了一个JDBC 抽象层，可以消除冗长的JDBC 编码合解析数据库厂商特有的错误代码，这个模块包含了spring 对于JDBC 数据访问进行封装的所有列
2. spring-orm 提供对象/关系映射技术的支持 如JPA JDO  Hibernate ibatis  等，提供了一个交互层，利用ORM 封装包，可以混合使用所有SPringle 提供的特性进行O/T 映射
3. spring-tx 提供编程式和声明式事务支持
4. spring-OXM 提供对象/xml 映射技术的支持 
5. spring-JMS 提供对JMS 的支持（邮件服务） 主要包括一些制造合消费信息的特性
6. spring-transaction 支持变成合声明性事务管理。 这些事务类必须实现特点的接口，并且对所有的pojo 都使用

spring 提供了大量基于spring的项目，可以用来更深入地降低我们的开发难度，提高开发效率

1. spring boot 使用默认开发配置来实现快速开发
2. spring XD 用来简化大数据应用开发
3. spring cloud 为分布式系统开发提高工作集
1. spring data  对主流关系型和nosql 数据库的支持
1. spring batch 简化及优化大量数据的批处理操作
1. spring security  通过认证和授权饱和应用
1. spring social 与社交网络API （新浪 ，Facebook ）的继承
1. spring amqp 基于amqp 的消息的支持
1. spring mobile 提高对手机设备检测的功能，给不同设备返回不同页面的支持 支持移动web 应用开发
1. spring for Android 主要提供在Android 上消费restful api 的功能
1. spring web flow  基于spring mvc 提供基于向导流程式的web应用开发
1. spring web service  提供基于协议有限的soap web 服务
1. spring ldap 简化使用LDAP 开发
1. spring session  提供一个api 以及实现来管理用户会话信息

