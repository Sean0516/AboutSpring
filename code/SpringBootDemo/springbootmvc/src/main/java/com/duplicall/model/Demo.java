package com.duplicall.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description Demo
 * @Author Sean
 * @Date 2020/5/12 14:55
 * @Version 1.0
 */
@XmlRootElement(name = "demo")
public class Demo {
    private String name;
    private String sex;

    public Demo() {
    }

    public Demo(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
