package com.duplicall;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description AccountInfo
 * @Author Sean
 * @Date 2021/6/28 17:46
 * @Version 1.0
 */
public class AccountInfo {
    private String username;
    private String pwd;
    private String email;
    private String hrStatus;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHrStatus() {
        return hrStatus;
    }

    public void setHrStatus(String hrStatus) {
        this.hrStatus = hrStatus;
    }

    public AccountInfo() {
    }

    public AccountInfo(String username, String pwd, String email, String hrStatus) {
        this.username = username;
        this.pwd = pwd;
        this.email = email;
        this.hrStatus = hrStatus;
    }

    public static void main(String[] args) {
//        String msg = "{\"CODE\":\"0102\",\"EMP_ID\":\"722\",\"STRJOBTITLE\":\" \",\"EMPSTATUS\":\"1\",\"LDEPARTMENTID\":\"2180\",\"STRNAME\":\"临时工27\",\"ORGNAME\":\"临时工号\",\"ROLE_FLAG\":[\"java.util.ArrayList\",[[\"org.springframework.util.LinkedCaseInsensitiveMap\",{\"ROLEID\":[\"java.math.BigDecimal\",100002],\"ROLENAME\":\"中心座席\"}],[\"org.springframework.util.LinkedCaseInsensitiveMap\",{\"ROLEID\":[\"java.math.BigDecimal\",100003],\"ROLENAME\":\"中心座席2\"}]]],\"DATA_ROLE\":\"1\",\"RESOURCELIST\":[\"java.util.ArrayList\",[[\"org.springframework.util.LinkedCaseInsensitiveMap\",{\"RESOURCEID\":\"M01010210\"}]]]}\n";
//        JSONObject object = JSON.parseObject(msg);
//        String loginId = object.getString("CODE");
//        String userName = object.getString("EMP_ID");
//        String orgName = object.getString("LDEPARTMENTID");
//        JSONArray role = object.getJSONArray("ROLE_FLAG");
//        JSONArray roleList = role.getJSONArray(1);
//        for (int i = 0; i < roleList.size(); i++) {
//            JSONArray object1 = (JSONArray) roleList.get(i);
//            JSONObject roleObj = object1.getJSONObject(1);
//            String roleName = roleObj.getString("ROLENAME");
//        }

//        List<User> users=new ArrayList<>();
//        users.add(new User("222","123"));
//        users.add(new User("223","123"));
//        users.add(new User("224","123"));
//        users.add(new User("225","123"));
//        Map<String, Optional<User>> collect = users.stream().collect(Collectors.groupingBy(User::getCode, Collectors.reducing((user, user2) -> user)));


    }

    private static void demo(JSONObject object) {
        System.out.println("STRNAME" + object.getString("STRNAME"));
        JSONArray orgpid = object.getJSONArray("ORGPID");
        if (Objects.nonNull(orgpid)) {
            System.out.println("ORGPID = " + orgpid.getString(1));
        }
        JSONArray orgid = object.getJSONArray("ORGID");
        if (Objects.nonNull(orgid)) {
            System.out.println("ORGID= " + orgid.getString(1));
        }

        JSONArray children = object.getJSONArray("CHILDREN");
        JSONArray jsonArray = children.getJSONArray(1);
        for (int i = 0; i < jsonArray.size(); i++) {
            String string = jsonArray.getString(i);
            JSONArray array = JSON.parseArray(string);
            JSONObject jsonObject = array.getJSONObject(1);
            if (jsonObject.containsKey("CHILDREN")) {
                demo(jsonObject);
            }
        }

    }
}
