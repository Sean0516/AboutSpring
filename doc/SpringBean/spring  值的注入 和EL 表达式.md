在spring 中 处理外部值的最简单的方式是通过声明属性源并且通过spring 的 environment 来检索属性  通常是使用@PropertySource 引用文件名和文件路径 。 这样这个属性文件就会被加载到spring的environment中 在需要使用属性的地方通过调用 getProperty 来获取 environment 中的属性值


```
   @Autowired
    private ApplicationContext applicationContext;
   String property = applicationContext.getEnvironment().getProperty("user.name");

```
### spring boot 将properties 文件中的属性注入为一个Bean 对象

```
@Configuration
@PropertySource(value = "classpath:config/user.properties")
@ConfigurationProperties(prefix = "user")
public class UserPropertiesConfig {
    private String name;
    private int age;
    private String password;
    }
```


Spring 开发中经常涉及调用各种资源的情况，包括普通文件，网址，配置文件，环境变量。使用spring 表达式语言实现资源的注入 ，能够以一种强大和简单的方式将值装配到bean 属性和构造器中

spring EL 的特性
1. 使用bean 的id 来引用bean
1. 调用方法和访问对象的属性
1. 对值进行算术，关系 和逻辑运算
1. 正则表达式匹配
1. 集合操作

### EL 表达式

1. 普通字符

```
    @Value("12")
    private String  str;
```


2. 操作系统属性

```
   @Value("#systemProperties['os.name']")
    private String  osName;
    @Value("#{systemProperties['os.name']}")
    private static String  osName;
```


3. 表达式运算结果。

4. 其他bean的属性

```
@Value("#UserServer.name")
```


5. 文件内容

```
    @Value("classpath:test.txt")
    private Resource testFile;
```

6. 网址内容

```
    @Value("http://www.baidu.com")
    private Resource testUrl;

```

7. 属性文件

```
@value("${book.name}")
    private String  bookName;
```
