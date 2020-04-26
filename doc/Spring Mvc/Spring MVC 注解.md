Spring 常用注解有以下
### @Conttoller 
用来表示注解的类做为MVC 框架的一个controller 的主要注解 ，dispatcher servlet 扫描被@controller 注解的类，从而将web请求映射到被@requestmapping 注解的方法上。


```
@Controller
@RequestMapping("xxx")
piblic class test(){
}
```
### @RequestMapping
@requestmappering注解用来将用户请求映射到处理器类或者方法，可以在类级别或者方法级别应用该注解。使用@requestmapping注解的方法可以允许有非常灵活的签名，他可以接受HTTP servlet 请求/响应对象，HTTP session对象，inputstream  output stream 对象，path variable /modeattribute 注解参数，bingdingresult 对象以及其他对象

### @ResponseBody
支持将放回值放在response体内 而不是放回一个页面， 单纯的返回数据

### @PathVariable
将一个方法参数绑定到一个URL 模板 ，如果可以通过URL 从用户获取数据 ，将有利于处理器方法的执行，所以通过 @PathVariable  从URL 中获取数据传给后台  @PathVariable 参数可以是任何类型 ，如 int date string

```
 @RequestMapping(value = "info/{id}", method = RequestMethod.POST)\
  public ResponseEntity xxxx(@PathVariable String id,)  {
}
```
### @RestController

@Controller  和@ResponseBody 注解的组合，以为着该类的所有url 返回的都是数据，而不是页面


### @ConttollerAdvice
将代码集中到一个地方，以便跨控制器共享代码  ConttollerAdvice 注解的类可以包含有  ExceptionHandler InitBinder 和 ModeAttribute  ，这些方法可以被应用到应用程序中所有带有@RequestMapping 注解的方法中

### @ModeAttribute
该注解使用一个向视图公开的建将一个返回值与一个参数在一起，可以在方法级别或方法的参数上应用该注解

### @InitBinder
用来初始化webDataBinder方法，比如之策自定义编辑器，以便解析日期字
### @ExceptionHandler
定义方法来处理控制器类发生的异常,通常为全局 controler 异常控制器


