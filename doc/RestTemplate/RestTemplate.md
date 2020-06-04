RestTemplate是Spring提供的用于访问Rest服务的客户端，RestTemplate提供了多种便捷访问远程Http服务的方法，能够大大提高客户端的编写效率。RestTemplate对象在底层通过使用java.net包下的实现创建HTTP 请求，可以通过使用ClientHttpRequestFactory指定不同的HTTP请求方式。

### Get 请求
通过如下两种方法进行调用实现
1. getForEntity 该方法返回的是 ResponseEntity  该对象是spring 对HTTP 请求响应的封装， 存储了HTTP 的重要元素 ，例如  HTTP status ，httpheaders 以及泛型类型的请求体对象  ，该函数有三种不同的重载实现


```
getForEntity (String url,Class responseType ,Object... urlParams) 

```
其中 url为请求地址，responseType 为请求响应体body的包装类型，urlParams 为url 中的参数绑定，该参数是一个数组，根据url 参数的数据，设置值，不过一般习惯使用map 的方式来绑定参数 ，参考下面
```
getForEntity (String url,Class responseType ,Map urlParams)
```
 提供map 类型的参数，在对url 进行参数绑定时，只需要在占位符中指定map 的key 和value  key 和url 中的参数一致 类似 http:test/ext?id={}&name={name} 该map就应该以id 和 name 作为key 
getForEntity (URI uri,Class responseType)
该方法使用URI 来指定url 和url 参数 。

2. getForObject 对第一种方法的进一步封装，实现直接返回包装好的对象内容。 当不需要关注请求响应除body外的其他内容时，该函数就非常好用。 它的重载实现和 上面是一样的

### Post 请求
在restTemplate 种 ，对于post 请求可以通过三种方法来进行调用
1.  PostForEntity  同get请求的方法getForEntity 类似。PostForEntity  有三种不同的重载方法

```
postForEntity(String url,Object request,Class responseType,Object... urlParams) ;
postForEntity(String url,Object request,Class responseType,Map... urlParams) ;
postForEntity(URI uri,Object request,Class responseType) ;
```
这些方法和上面get请求类似 ,区别在于request ，该参数是post 请求需要的参数对象 如果参数是一个普通对象， restTemplate会将该对象转换为一个 HttpEntity 来处理，Object 是request的类型， request 的内容会被视作完整的body来请求 如果是HttpEntity 对象，那么会被视作一个完整的HTTP 请求对象来处理。  这个request 种不仅包含一个body 的内容，也包含了header的内容 
2.  postForObject 函数 该方法也和getForObejct 类似。也有三种重载方法

```
postForObject (String url,Object request,Class responseType,Object... urlParams) ;
postForObject (String url,Object request,Class responseType,Map... urlParams) ;
postForObject (URI uri,Object request,Class responseType) ;
```

3.  postForLocation  实现以post请求提交资源，并返回心的资源文件URI 

```
postForLocation  (String url,Object request,Object... urlParams) ;
postForLocation  (String url,Object request,Map... urlParams) ;
postForLocation  (URI uri,Object request) ;
```
由于该函数会返回新资源的URL ，该URL相当于指定了返回类型，所以不需要指定返回类型
### PUT 请求 
put函数作为void类型，所以没有返回内容。 所以不需要定义 responseType 参数。 除此之外，其他参数定义与用法和postForObject 一致
### DELETE 请求

```
delete(String url,Object request,Object... urlParams) ;
delete(String url,Object request,Map... urlParams) ;
delete(URI uri,Object request) 
```
参数定义与用法和put方式类似

### EXCHANGE 请求
EXCHANGE 方法 ，主要是使用统一的方法模板进行 GET POST PUT DELETE 四种方法  主要是使用httpMethod 参数来表明要使用的HTTP 动作，根据这个参数的值，exchange 能够执行与其他restTemplate  方法一样的工作

```
restTemplate.exchange(
        String url, 请求地址
        HttpMethod method,请求方式
        HttpEntity requestEntity,   请求实体， 封装请求头 ，请求内容
        Class responseType,  响应类型 ，由服务器返回类型决定
        Object uriVariables[] url 中的参数变量
    )
```
一般来说， restemplate 提供的GET POST PUT DELETE  方法住够很多的业务场景了，但是由于PUT 和DELETE 方法没有返回值。 当需要返回值的时候，就可以使用exchange方法来自定义 请求方法 ，以得到返回值

```
 HttpHeaders headers = new HttpHeaders(); 	 	
 headers.setContentType(MediaType.APPLICATION_JSON);
// HttpHeaders 可以设置很多请求头类型，具体情况由自己的实际情况来设置
 HttpEntity<String> entity = new HttpEntity<String>("test",headers);
```

ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.POST, entity, String.class); 
如果url 中有参数的话，可以使用map来存放参数 
restTemplate.exchange(url, HttpMethod.POST, entity, String.class ， Map<String, ?> uriVariables); 
Put 请求
ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class); 
 



