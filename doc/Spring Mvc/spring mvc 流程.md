Spring MVC 将用户请求在 Dispatcher Servlet （前端控制器） --> 处理器映射器--> 处理器控制器-->视图解析器之间移动
### spring mvc 的请求流程


```
graph LR
用户请求--> DispatcherServlet前端控制器
DispatcherServlet前端控制器--> 处理器映射器
DispatcherServlet前端控制器--> 处理器控制器
DispatcherServlet前端控制器--> 视图解析器
DispatcherServlet前端控制器--> 视图
处理器控制器--> 模型及逻辑视图名
模型及逻辑视图名--> DispatcherServlet前端控制器
视图--> 响应用户
```
Spring MVC 的核心元素就是DispatcherServlet，它是主要的servlet，用于负责处理所有的请求，并将请求调度到何时的通道，通过使用Dispatcher Servlet ，spring mvc 采用了一种前端控制器模式，该模式提供了一个入口点来处理web 应用程序的所有请求

Dispatcher Servlet 配合使用处理器映射器 和视图解析器来确定根据用户请求执行那个业务逻辑，以及处理完毕之后向用户返回什么内容，处理器映射对象实现了 handerMapping 接口，这些对象在映射和处理器对象之间提供了一个桥梁，而视图解析器对象实现了ViewResolver 接口，这些对象根据名称解析不同的视图，下面是执行的流程

1. 根据Http 请求 Dispatcher Servlet 通过处理器映射来确定执行那个控制器，然后Dispatcher Servlet 通过处理器适配器调用实际的处理器方法，转发请求，并且期待返回模型和视图
2. 调用容器内的处理器方法来处理业务逻辑。 该方法首先设置模型数据（这些数据被传递给视图），然后向Dispatcher Servlet 返回视图名称，而视图将作为响应显示给用户
3. Dispatcher Servlet 集成视图解析器并根据解析器配置确定合适的视图
4. Dispatcher Servlet 将模型传递给视图，并在浏览器中显示视图




