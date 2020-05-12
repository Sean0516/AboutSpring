package com.duplicall.controller;

import com.duplicall.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description RestApiController
 * @Author Sean
 * @Date 2020/5/12 15:51
 * @Version 1.0
 */
@Api(value = "RestApi", description = "Rest Api Server")
@RestController
@RequestMapping("api")
public class RestApiController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     *需要注意的是  必须指定 RequestMethod  的具体类型，不然前端会显示多种类型的Rest
     */
    @ApiOperation(value = "信息总览", notes = "用户信息总览")
    @ApiImplicitParam(name = "type", value = "获取信息的类型（name ,sex）", paramType = "query", dataType = "string")
    @RequestMapping(value = "info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUserInfo(String type) {
        System.out.println(type);
        return new ResponseEntity("this is user info ", HttpStatus.OK);
    }

    @ApiOperation(value = "userInfo By Json", notes = "获取用户信息 使用JSON  格式")
    @RequestMapping(value = "userInfo", method = RequestMethod.GET ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseEntity userInfo() {
        User user = new User();
        user.setName("sean");
        user.setAge(22);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @ApiOperation(value = "userInfo By Xml", notes = "获取用户信息 使用 XML  格式")
    @RequestMapping(value = "userInfo", method = RequestMethod.GET, produces = {"application/xml;charset=UTF-8"})
    @ResponseBody
    public ResponseEntity userInfoXml() {
        User user = new User();
        user.setName("sean");
        user.setAge(22);
        return new ResponseEntity(user, HttpStatus.OK);
    }

}
