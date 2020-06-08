MongoDB 是一个基于文档的存储型数据库，使用面向对象思想，每一条数据记录都是文档的对象

spring 为MongoDB 提供了以下支持

1. 映射注解支持
@Document 映射领域对象与mongoDB 的一个文档（collocation）
@Id  映射当前属性是 ID
2. mongoTemplate
通过mongoTemplate 可以进行数据相关操作， 一般配合使用的有Query 和Criteria  两个类，主要是用于查询和参数设置

Spring Boot开发流程 

1. 依赖
2. mongo db  服务器地址配置
3. 实体类的创建 ，必须序列化
4. 使用mongotemplate 完成具体代码的编写

