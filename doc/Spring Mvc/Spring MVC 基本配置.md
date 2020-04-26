Spring MVC 定制配置需要配置类继承 一个WebMvcConfigurerAdapter，通过重写这个类的方法，来完成常用的配置.包括静态资源访问 ， 视图控制器 ，拦截器，路径匹配的配置等等


```
@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 配置静态资源访问
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * addResourceHandler 对外暴露的访问路径   addResourceLocations 文件存放的路径
         */
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        super.addResourceHandlers(registry);
    }

    /**
     * 配置拦截器，自定义拦截器，以及拦截器拦截的uri
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor()).addPathPatterns("/test1/**");
        super.addInterceptors(registry);
    }

    /**
     * 视图控制器
     *对于页面转向的controller时，如果没有任务业务处理的时候，只是简单的页转向，可以通过在webmvc 配置类中重写addviewcontrollers 来简化配置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("viewUser").setViewName("user/viewUser");
        super.addViewControllers(registry);
    }

    /**
     * 路径匹配设置
     * 在springmvc 中，如果访问路径带有“.”（例如带有小数点的参数），则后面的值会被忽略。如果不想忽略的话，可以重写 configurepathmath来来实现 
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
        super.configurePathMatch(configurer);
    }
    
}
```
路径匹配参数配置是针对所有的url 路径，也可以针对特定的url 来实现
只需要在url 参数后添加 :.+

```
/user/findPassword/{email:.+}

```

