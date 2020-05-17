package com.test.ssm.blog.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/*
用户
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -4415517704211731385L;
    private Integer userId;    //用户ID
    private String  userName;  //用户名称
    private String userPass;   //用户登录密码
    private String userNickname;  //用户昵称
    private String userEmail;    //邮件
    private String userUrl;     //网址
    private String userAvatar;  //头像
    private String userLastLoginIp;  //上一次登录IP
    private Date userRegisterTime;    //注册时间
    private Date userLastLoginTime;   //上一次登录时间
    private Integer userStatus;      //状态
    private Integer userAccess;     //用户权限
    private Integer articleCount;   //文章数量,不是数据库的字段
}
