package com.duplicall.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description CustomFilter
 * @Author Sean
 * @Date 2020/5/25 11:30
 * @Version 1.0
 */
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
