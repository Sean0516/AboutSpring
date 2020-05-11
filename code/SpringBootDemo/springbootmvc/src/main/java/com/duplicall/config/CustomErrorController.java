package com.duplicall.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description CustomErrorController
 * @Author Sean
 * @Date 2020/5/11 12:20
 * @Version 1.0
 */
@Controller
public class CustomErrorController implements ErrorController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ErrorAttributes errorAttributes;
    private final static String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * 网页请求返回信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request) {
        Map<String, Object> map = getErrorAttributes(request, false);
        // 自定义error 页面路径
        ModelAndView mav = new ModelAndView("error/error", map);
        return mav;
    }

    /**
     * rest  接口调用 返回信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request));
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(body, status);
    }

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception ex) {
                logger.error("get state error [{}]", ex.getMessage(), ex);
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }


    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map<String, Object> map = this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
        String URL = request.getRequestURL().toString();
        map.put("URL", URL);
        return map;
    }
}
