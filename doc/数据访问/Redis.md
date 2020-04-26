Redis  是一个可以持久存储的缓存系统。 是一个高性能的key-value 数据库，使用键值对来存储数据

redis 提供给了 多种数据类型用来存储数据

1. Key 可以是任意类型，但是最终会存储为 byte[] 
1. String  简单的（key value）存储接口，支持数据的自增，支持BitSet 结构
1. Hash	哈希表数据结构，支持对field 的自增等操作
1. List 列表 ，支持按照索引，索引范围获取元素以及Pop Push  等堆栈操作
1. set 集合 及Zset 有序集合

在存入redis  时要生成一个唯一的key 在查询和删除数据时，可以使用找个key 进行相关操作

redis 的功能非常强大，既可以作为数据库也可以作为缓存，还能当作队列，总体来讲，有以下用途

1. 简单的string, 可以作为 memcached替代品，用作缓存系统
2. 使用list 的pop 和Push 功能 可以作为阻塞队列/非阻塞队列
3. 使用subscribe 和publish 实现发布/订阅模式
4. 对数据进行实时分析。
5. 使用setz 做去重的计数统计
6. 使用SortedSet  做排序

由于redis 存储是以内存为主，因此如何节省内存是非常关键的地方。
首先，key越短越好，可以采取编码或者简写的方式。 同时key的数量也需要控制，可以考虑使用hash做二级缓存存储来合并类似的key 从而减少key 到底数量

其次，value 也是越小也好，尤其是在存储序列化后的字节时，要选择最节省内存到底序列化方式。 
有一点需要注意 的是 ，在保存在redis  数据库中的数据默认是永久存储的，推荐指定一个时限来确定数据的声明周期，超过时间限制的数据会被删除。

StringRedisTemplate与RedisTemplate都是redis 用来操作数据的类  虽然StringRedisTemplate继承RedisTemplate，但是，两者的数据是不共通的；也就是说StringRedisTemplate只能管理StringRedisTemplate里面的数据RedisTemplate只能管理RedisTemplate中的数据 ，需要说明的是，由于RedisTemplate 使用的序列话是 jdk的序列化策略 ，这样对于数据库的查看并不是很方便，因为jdk 序列化使用的是二进制形式存储数据，在查看数据的时候，无法明确的查看到数据的具体key 

Spring 支持以下几种序列化方式，可以通过重写RedisTemplate  的序列化配置

1. StringRedisSerualizer简单的字符串序列化，使用的是String.getBytess()方法，StringRedisTemplate 就是使用他作为key value hashKey 以及hashValue 的序列化实现的
2. GenericToStringSerializerr 可以将任何对象泛化为字符串序列化，对于每一种对象类型都有不同的实现
3. Jackson2JsonRedisSerializer  JSON 的序列化方式，使用jackkson mapper 将object 序列化为json字符串
4. JdkSerializationRedisSerializer 使用jdk自带的序列化机制，也是直接使用RedisTemplate 时使用的序列化机制。

### spring boot 使用 redis  的基本步骤
1. 添加依赖
2. 配置yml文件 主要是配置redis 连接信息
3. 创建实体类 ，实现序列化
4. 如果使用RedisTemplate  则重写redis 配置文件
5. 使用RedisTemplate  编写具体的实现代码
