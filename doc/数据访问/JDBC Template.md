JdbcTemplate  被定义为一个Spring 管理的bean 该bean 是线程安全的，并且可以被不同的数据访问对象所共享，因此被定义为单利，JdbcTemplate  的bean主要依赖项为一个DataSource 对象， 所以，需要把创建的dataSource 对象注入到JdbcTemplate  中 .spring 将数据访问的样本代码抽象到模板类中  spring 为JDBC 提供了两种类型的模板类供给选择

1 .JdbcTemplate  ：最基本的jdbc模板，这个模板支持简单的数据库访问功能，以及基于索引参数的查询 
2 NamedParameterJdbcTemplate 查询时可以将值以命名参数的形式绑定到SQL 中，而不是使用简单的索引参数

一般来说，jdbcTemplate 是最好的选择方案， 只有在需要使用命名参数的时候，才需要使用到NamedParameterJdbcTemplate 

### JdbcTemplate 查询

JdbcTemplate 通过不同的重载白本提供了各种方法来执行查询，并将查询结果作为应用程序中不同对象类型进行处理，有 query()  queryForObject() queryForList() queryForMap() queryForRowSet()  方法以及他们多个不同的重载方法（接收不同输入参数，比如 查询字符串，查询输入参数值，类型，结果对象类型等）

JdbcTemplate  的query()  queryForObject() 方法完全遵循了相同的方法，他们接收查询字符串，查询参数以及RowMapper 类型的回调对象作为输入参数

JdbcTemplate  可以使用RowMapper 将ResultSet 中返回的每一行映射到一个结果对象。通常作为一个输入参数传递给JdbcTemplate  ，当然也可以使用new BeanPropertyRowMapper<>(User.class) 直接输入对象类来返回结果对象

queryForList() queryForMap()  方法采用了一种更简单方法，queryForList() 方法执行查询并换回一个List 。queryForMap()  返回一个Map 该map 的键为列名

### JdbcTemplate 插入，更新 删除

所有的插入，更新 删除 SQL 操作都是使用JdbcTemplate  的update方法完成   ，也支持使用preparedstatements 来传参数
