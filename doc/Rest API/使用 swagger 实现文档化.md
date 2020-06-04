Swagger2 的出现就是为了从根本上解决上述问题。它作为一个规范和完整的框架，可以用于生成、描述、调用和可视化 RESTful 风格的 Web 服务：

1. 接口文档在线自动生成，文档随接口变动实时更新，节省维护成本
2. 支持在线接口测试，不依赖第三方工具

集成 swagger2 
1. 添加依赖

```
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.7.0</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.7.0</version>
        </dependency>
```

2.  配置 Swagger2 cofig，通过这些配置可以指定在spring-boot启动时扫描哪些controller层的文件夹。另外可以指定API文档页的标题和描述信息等内容

```
@Configuration
@EnableSwagger2
public class Swagger2 {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.duplicall"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(" RESTFUL APIs")
                .description(" Rest  Server api接口文档")
                .version("1.0")
                .build();
    }
```
3.  rest api 接口编写


```
@Api(value = "RestApi", description = "Rest Api Server")
@RestController
@RequestMapping("api")
public class RestApiController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     *需要注意的是  必须指定 RequestMethod  的具体类型，不然前端会显示多种类型的Rest
     */
    @ApiOperation(value = "信息总览", notes = "用户信息总览")
    @ApiImplicitParam(name = "type", value = "获取信息的类型（name ,sex）", paramType = "query", dataType = "string")
    @RequestMapping(value = "info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUserInfo(String type) {
        System.out.println(type);
        return new ResponseEntity("this is user info ", HttpStatus.OK);
    }
}
```

