Statement 在执行一个查询的时候，数据库将首先查询字符串，然后在执行之前进行解析，编译以及计算执行计划， 如果多次执行相同的查询，那么该预处理步骤就会成为一个性能瓶颈，而如果使用PreparedStatements  ，则该预处理仅被执行一次，因此，会减少执行时间，相比于每次动态创建查询字符串，使用PreparedStatements的另外一个好处就是保护系统免受sql 注入供给，PreparedStatements执行的查询使用起来更安全

JdbcTemplate 对PreparedStatements提供了方法，主要是将普通的参数改为 PreparedStatementSetter 来设置传入的参数  方法如下


```
query(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper)
  List<User> userList = jdbcTemplate.query("select * from user where id = ?", preparedStatement -> preparedStatement.setInt(1,id), new BeanPropertyRowMapper<>(User.class));
query(PreparedStatementCreator psc, RowMapper<T> rowMapper)

```
