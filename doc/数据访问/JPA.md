JPA 主要用于在Java处理持久化操作，它对企业级Java中所使用的ORM 特性和功能进行标准化 ，分别定义了用来将对象模型映射到关系模型的API 可以在对象上执行CRUD 操作，一种对象查询语言以及通过对象图获取数据的标准API

### 将对象模型映射到关系模型
任何ORM 工具都将对象模型映射到关系模型，该映射是执行其他运行时操作的前提-- 比如执行CRUD 操作或使用一种对象查询语言查询数据。 对象-关系映射通常发生到以下四种之间
1. 类和表
1. 类的属性和表中的列
1. 对象关联和外键
1. Java类型和sql 类型

### 定义实体
如果一个对象在数据库端与一个带有主键的记录相对应，那么该对象就称为实体，一个实体类与其对应的数据库表之间通常是一对一映射关系， 然而，一个实体也可能和多个表相映射。 

```
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "name")
    private String name;
}
```
实体类应该是顶层类，他们不必扩展自特殊的超类或者实现任何接口。同时也不必将他们序列化。 JPA 为映射提供了Java 注解，这些注解位于javax.persistence 包中。

@Table(name = "user") 注解指定了表名，如果没有指定，则使用类名作为表名，JPA 配置中的每一个实体都应该是独一无二的名称，默认情况下，该名称就是类的简单名称

@Id 注解标记了主键属性，主键列的名称与该属性名相匹配，如果列名不同，可以使用@Column 注解更改其名称

@GeneratedValue 注解告诉JPA ，应用程序将不会负责分配主键值，而是由jpa供应商处理

### 对象之间关联关系
对象之间存在多种不同类型的关联

一对一 ：在一对一关联中，只有两个对象彼此相互关联 

```

@Entity
public class Role{}


@OneToOne
@JoinCloumn (name="role_id")
private Role role;

```
多对一 

```
@ManyToOne
@JoinCloumn (name="role_id")
private Role role;
```
一对多 多对多

Spring Data JPA 是SpringData的一个子项目，它通过提供基于JPA的Repository极大了减少了操作JPA的代码。由SpringBoot 提供的JPARepository真的是非常简单。基本上大部分的业务都可以满足了

配置文件中jpa.hibernate.ddl-auto是hibernate的配置属性，其主要作用是：自动创建、更新、验证数据库表结构。

该参数的几种配置如下：

 create：每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。

create-drop：每次加载hibernate时根据model类生成表，但是sessionFactory一关闭,表就自动删除。

update：最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库），以后加载hibernate时根据model类自动更新表结构，即使表结构改变了但表中的行仍然存在不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等应用第一次运行起来后才会。

validate：每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。
    









