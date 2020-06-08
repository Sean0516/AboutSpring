spring  支持自定义Controller 异常。 分别为是通过实现 ErrorController 来自定义错误返回，或者是使用@ControllerAdvice 和@ExceptionHandler 注解来实现全局异常配置。 需要注意的是，这两种方法@ControllerAdvice 只针对与controller ，而 ErrorController 则针对于全局异常，包括 404 ,401 ,500 等自定义页面或自定义返回json数据。如果Controller 异常，则会以ControllerAdvice 为准
 
### 自定义ErrorController
自定义ErrorController 主要分为页面返回，和rest 接口调用返回 demo 代码如下

```
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


    private Map<String, Object> getErrorAttributes(HttpServletRequest request,
                                                   boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map<String, Object> map = this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
        String URL = request.getRequestURL().toString();
        map.put("URL", URL);
        return map;
    }
}
```

error.html 

```
<!DOCTYPE html >
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h2>this is error   page  </h2>
<p class="title"> code is : <span  th:text="${status}"></span> </p>
<div th:text="${#dates.format(timestamp,'yyyy-MM-dd HH:mm:ss')}"></div>
<div th:text="${message}"></div>
<div th:text="${error}"></div>
</body>
</html>
```

### ExceptionHandler
```
@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private JSONObject jsonObject = new JSONObject();

    @ExceptionHandler(value = Exception.class)
    public Object defaultErrorHandler(Exception e) {
//        可以对异常信息进行自定义封装
        logger.error(e.getMessage());
        jsonObject.put("code", -1);
        jsonObject.put("message", e.getMessage());
        ResponseEntity responseEntity = new ResponseEntity(jsonObject, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

}
```



