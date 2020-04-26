spring  支持声明式事务，使用注解来选择需要使用事务的方法，当然，也可以对类使用注解，当类和方法同时使用事务注解时，类级别的注解会重载方法级别的注解 spring的事务，主要是基于AOP 的实现操作。在使用了声明式事务的注解的方法被调用时，spring会开启一个新的事务，当方法无异常运行结束后，spring会提交这个事务，否则回滚

Spring 使用@EnableTransactionManagement  注解激活基于注解的声明式事务管理，  程序在运行中Spring 容器会主动扫描@Transactional 注解的方法 和类 ，当找到后，spring 容器创建一个封装了实际bean 实例的代理  ，到调用bean中的方法时，首先由该代理实例截获该调用，并检查是否开始事务，如果是 ，则开始事务，然后调用实际目标bean 的方法来执行业务逻辑，当目标bean 的方法返回后，代理实例提交事务，并返回，如果事务方法中抛出异常Spring 将检查异常类型，以便确认是提交事务还是回滚 ，整个异常由 Transactional  rollbackFor  noRollbackFor  指定的异常来决定需要进行回滚的异常是那些（当然 springboot 中，无需显示的开启使用@EnableTransactionManagement 因为spring boot 自动开启了注解事务的支持）

 Spring使用@Transactional 注解来进行事务声明，并设置对应的注解行为 ，例如 rollbackfor 等等 ，需要注意的是，如果在使用注解的方法里面使用了try  catch  需要在catch 块中，当处理完异常后，手动将异常抛出
 
 ### 自定义事务行为
 在事务划分期间，可以使用Transactional  注解提供的一组默认属性，当然可以根据需要来更改默认的事务行为（在Transactional 添加对应的属性即可）
 
 ##### propagation  
 propagation定义了事务范围，是否跨越多个方法调用等，主要有以下几种选择（默认值为REQUIRED ）
 1. REQUIRD 方法A 新建一个事务，当方法A调用方法B 的时候，方法B 也将使用相同的事务，如果方法B 发生异常需要回滚，则整个事务数据回滚
 2. REQUIRES_NEW 对于方法AB ，在调用方法时每个方法都开启一个事务，如果方法B 有异常，则只回滚对应方法的事务
 3. NESTED 与REQUIRES_NEW类似。但是没有两个独立的事务，只有一个跨不同方法调用的活动事务 ，通过保存点来标记新的方法的调用，当在第二个方法发生异常时，在最后一个保存点之前的事务都将回滚
 ##### isolation 
指定了底层数据库的隔离级别 （默认为DEFAULLT）
##### timeout 
指定事务超时时间。默认为当前数据库的事务超时时间，可以直接将其传递给底层数据库
##### readOnly 
指定当前事务是否只读事务  默认为false
##### rollbackFor  
指定某个，或者某些异常可以引起事务回滚，
##### noRollbackFor 
指定某些异常不可以引起事务回滚
### 事务的级别

类级别的事务 ：在类级别使用Transactional，如果在类级别使用注解，那么类中的所有的公共方法都被事务

方法级别的事务： 在方法上使用Transactional  。需要注意的是 ，如果一个类中，同时存在类级别和方法级别事务 方法级别的Transactional 注解会重写在类级别使用的事务行为


