package com.starter.duplicall;

/**
 * @Description SeanBean
 * @Author Sean
 * @Date 2021/11/8 16:19
 * @Version 1.0
 */
public class SeanBean {
    private SeanProperties seanProperties;

    public SeanBean(SeanProperties seanProperties) {
        this.seanProperties = seanProperties;
    }
    public String info(){
        return "name: "+ seanProperties.getName() +"age:"+ seanProperties.getAge();
    }
}
