Rest Api 可以根据用户请求头的不同 ，返回不同的媒体类型的响应（JSON XML 等）
在默认的情况下，Spring 会安装应用所定义的内容协商策略解析正确的内容 （用户可以根据指定 Accept 头信息来返回不同类型的信息）

### XML 格式数据的实现
1. 在需要返回的bean 上面使用@XmlRootElement (name="") 这个name  代表这个xml 根节点的名称
2. 在controller 中 对需要返回的接口的RequsetMapper （）中添加 produces属性 并使其等于 不同的媒体类型（application/json;charset=UTF-8 或者 application/xml;charset=UTF-8）


```
  @RequestMapping(value = "/demo", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"})
  @ResponseBody
    
```


