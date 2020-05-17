package com.test.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/*
页面
 */
@Data
public class Page implements Serializable {
    private static final long serialVersionUID = 3927496662110298634L;
    private Integer pageId;   //页面ID
    private String pageKey;   //页面关键字
    private String pageTitle;  //页面标题
    private String pageContent;  //内容
    private Date pageCreateTime;  //创建时间
    private Date pageUpdateTime;  //更新时间
    private  Integer pageViewCount;  //查看数量
    private Integer pageCommentCount;  //评论数量
    private Integer pageStatus;   //状态


}
