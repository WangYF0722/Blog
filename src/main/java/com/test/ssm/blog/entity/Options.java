package com.test.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Options implements Serializable {
    private static final long serialVersionUID = -776792869602511933L;
    private Integer optionId;
    private String optionSiteTitle;   //博客站点名称
    private String optionSiteDescrption;  //描述
    private String optionMetaDescrption;  //主要描述
    private String optionMetaKeyword;   //关键字
    private String optionAboutsiteAvatar;  //头像
    private String optionAboutsiteTitle;   //简介
    private String optionAboutsiteContent;   //内容
    private String optionAboutsiteWechat;  //有关博客的微信
    private String optionAboutsiteQq;   //qq
    private String optionAboutsiteGithub;  //github
    private String optionAboutsiteWeibo;  //微博
    private String optionTongji;   //统计
    private Integer optionStatus;  //状况



}
