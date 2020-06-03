package com.duplicall.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * @Description CustomErrorHandler
 * @Author Sean
 * @Date 2020/5/28 21:19
 * @Version 1.0
 */
public class CustomErrorHandler extends DefaultResponseErrorHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        logger.info("response code [{}]", response.getStatusCode().value());
        return super.hasError(response);
    }
}
