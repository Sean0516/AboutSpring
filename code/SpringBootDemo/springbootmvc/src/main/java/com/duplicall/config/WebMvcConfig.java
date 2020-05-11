package com.duplicall.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.duplicall.config.filter.CustomInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * @Description WebMvcConfig
 * @Author Sean
 * @Date 2020/5/11 10:29
 * @Version 1.0
 */
@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 配置静态资源访问
     * addResourceHandler 对外暴露的访问路径   addResourceLocations 文件存放的路径
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/js/**").addResourceLocations("classpath:/js/");
        super.addResourceHandlers(registry);
    }

    /**
     * 跨域配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/cors/**")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET")
                .allowedOrigins("*");
        super.addCorsMappings(registry);
    }

    /**
     * 视图控制器
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("toView").setViewName("user");
        super.addViewControllers(registry);
    }

    /**
     * 路径匹配设置
     *
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //在spring mvc 中，如果访问路径带有“.” ，则后面的值会被忽略。如果不想忽略的话，可以重写 configure path math来来实现
        configurer.setUseSuffixPatternMatch(false);
        super.configurePathMatch(configurer);
    }

//    /**
//     * 消息内容转换配置
//     *
//     * @param converters
//     */
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //配置fastJson返回json转换
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        //修改配置返回内容的过滤
//        fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.DisableCircularReferenceDetect,
//                SerializerFeature.WriteMapNullValue,
//                SerializerFeature.WriteNullStringAsEmpty
//        );
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        converters.add(fastJsonHttpMessageConverter);
//        super.configureMessageConverters(converters);
//    }

    /**
     * 配置请求视图映射
     *
     * @return
     */
//    public InternalResourceViewResolver resourceViewResolver() {
//        InternalResourceViewResolver resourceViewResolver = new InternalResourceViewResolver();
//        //请求视图文件的前缀地址
//        resourceViewResolver.setPrefix("/WEB-INF/jsp/");
//        //请求视图文件的后缀
//        resourceViewResolver.setSuffix(".jsp");
//        return resourceViewResolver;
//    }
//
//    /**
//     * 视图解析器配置 自定义视图路径配置 （例如 jsp 文件时）
//     *
//     * @param registry
//     */
//
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.viewResolver(resourceViewResolver());
//        super.configureViewResolvers(registry);
//    }

    /**
     * 拦截器
     * addInterceptor：需要一个实现HandlerInterceptor接口的拦截器实例
     * addPathPatterns：用于设置拦截器的过滤路径规则
     * excludePathPatterns：用于设置不需要拦截的过滤规则
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor()).addPathPatterns("/demo").excludePathPatterns("/demo1");
        super.addInterceptors(registry);
    }
}
