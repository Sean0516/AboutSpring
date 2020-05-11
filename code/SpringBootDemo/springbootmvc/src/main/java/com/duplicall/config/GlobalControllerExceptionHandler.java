//package com.duplicall.config;
//
//import com.alibaba.fastjson.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
///**
// * @Description GlobalControllerExceptionHandler
// * @Author Sean
// * @Date 2020/5/11 16:03
// * @Version 1.0
// */
//@ControllerAdvice
//public class GlobalControllerExceptionHandler {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    private JSONObject jsonObject = new JSONObject();
//
//    @ExceptionHandler(value = Exception.class)
//    public Object defaultErrorHandler(Exception e) {
////        可以对异常信息进行自定义封装
//        logger.error(e.getMessage());
//        jsonObject.put("code", -1);
//        jsonObject.put("message", e.getMessage());
//        ResponseEntity responseEntity = new ResponseEntity(jsonObject, HttpStatus.INTERNAL_SERVER_ERROR);
//        return responseEntity;
//    }
//
//}
