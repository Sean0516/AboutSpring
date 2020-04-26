拦截器实现对每一个请求处理前后进行相关的业务处理 可以实现 HandlerInterceptor 接口 或者继承 HandlerInterceptorAdapter 类来实现自定义拦截器 

HandlerInterceptor 有三个方法可以重写 
### preHandle

在业务请求之前调用如果返回false从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链如果返回true执行下一个拦截器,直到所有的拦截器都执行完毕再执行被拦截的Controller然后进入拦截器链,从最后一个拦截器往回执行所有的postHandle()接着再从最后一个拦截器往回执行所有的afterCompletion()


### postHandle
在业务处理器处理请求执行完成后,生成视图之前执行的动作


### afterCompletion
在DispatcherServlet完全处理完请求后被调用,可用于清理资源等。当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()

拦截器自定义完成后。可以在 webMVC 的配置类中进行配置 。
 
```
   /**
     * 配置拦截器，自定义拦截器，以及拦截器拦截的uri
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor()).addPathPatterns("/test1/**");
        super.addInterceptors(registry);
    }

```
