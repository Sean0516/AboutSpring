package com.duplicall.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description Custom2Filter
 * @Author Sean
 * @Date 2020/5/25 15:48
 * @Version 1.0
 */
public class Custom2Filter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init custom2 filter [{}]", filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        logger.info("custom2 filter start deal request [{}]", httpServletRequest.getRequestURL());
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();
        logger.info("custom2 filter deal end  and spend time [{}]", end - start);
    }

    @Override
    public void destroy() {
        logger.info("custom2 destroy custom filter ");
    }
}
