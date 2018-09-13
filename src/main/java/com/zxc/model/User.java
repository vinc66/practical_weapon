package com.zxc.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;

    private String phone;

    private String passOrg;

    private String pass;

    private String realName;

    private String nick;

    private Integer gender;

    private Integer age;

    private String headPic;

    private Integer status;

    private Date cTime;

    private Date uTime;


}