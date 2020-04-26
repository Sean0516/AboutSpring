配置两套mapper ，两套sqlSessionFactory ，各自处理各自的业务。 互不干扰。核心思想就算配置多个数据源配置类

@MapperScan 扫描 Mapper 接口并容器管理，包路径精确到 master，为了和下面 cluster 数据源做到精确区分 
sqlSessionFactoryRef 表示定义了 key ，表示一个唯一 SqlSessionFactory 实例

使用这种方式，可以实现一个项目使用多个数据源。但是不易于维护和扩展


