package com.duplicall.core.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Sean
 * @since 2021-12-01
 */
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

    private String name;

    private Integer age;

    private String email;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }
    
    public Integer getAge() {
        return age;
    }

      public void setAge(Integer age) {
          this.age = age;
      }
    
    public String getEmail() {
        return email;
    }

      public void setEmail(String email) {
          this.email = email;
      }

    @Override
    public String toString() {
        return "User{" +
              "id=" + id +
                  ", name=" + name +
                  ", age=" + age +
                  ", email=" + email +
              "}";
    }
}
