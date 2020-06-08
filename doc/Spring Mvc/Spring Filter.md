Filter 过滤器主要是用来过滤用户请求的，它允许我们对用户请求进行前置处理和后置处理，比如实现 URL 级别的权限控制、过滤非法请求等等。Filter 是依赖于 Servlet 容器，Filter接口就在 Servlet包下面，属于Servlet规范的一部分。所以，很多时候我们也称其为“增强版 Servlet”
如果我们需要自定义 Filter 的话非常简单，只需要实现 javax.Servlet.Filter 接口，重写对应的方法即可

Filter同一个doFilter的方法来实现拦截，这个方法实现了对用户请求的过滤。具体流程如下


1. 用户发送请求到 web 服务器,请求会先到过滤器；
1. 过滤器会对请求进行一些处理比如过滤请求的参数、修改返回给客户端的 response 的内容、判断是否让用户访问该接口等等。
1. 用户请求响应完毕。
1. 进行一些自己想要的其他操作。

自定义 Filter 实现

1. 实现Filter 类，重写方法

```
public class CustomFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init custom filter ,and filter name [{}],", filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        logger.info("custom filter start deal request [{}]", httpServletRequest.getRequestURL());
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();
        logger.info("custom filter deal end  and spend time [{}]", end - start);
    }

    @Override
    public void destroy() {
        logger.info("custom destroy custom filter ");
    }
}
```


2. 在配置类中注册自定义过滤器 （对于多个拦截器，可以使用FilterRegistrationBean 的setOrder  来设置顺序）

```
@Bean
    public FilterRegistrationBean filter2RegistrationBean() {
        FilterRegistrationBean filterFilterRegistrationBean = new FilterRegistrationBean();
        // order 越大，过滤优先级越高
        filterFilterRegistrationBean.setOrder(2);
        filterFilterRegistrationBean.setFilter(new CustomFilter());
        filterFilterRegistrationBean.setUrlPatterns(Collections.singletonList("/demo"));
        return filterFilterRegistrationBean;
    }
```
