package com.test.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/*
评论
 */
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = -1038897351672911219L;
    private Integer commentId;  //ID
    private Integer commentPid;  //评论的评论需要，父ID
    private String commentPname;  //评论的评论需要，父名称
    private Integer commentArticleId; //评论的文章ID
    private String commentAuthorName; //作者名称
    private String commentAuthorEmail;  //作者邮件
    private String commentAuthorUrl;  //网址
    private String commentAuthorAvatar;  //图像
    private String commentContent; //内容
    private String commentAgent; //代理
    private String commentIp;; //IP
    private Date commentCreateTime; //创建时间
    private Integer commentRole;  //角色，管理员1，访客0
    private  Article article;  //非数据库字段。评论的文章

}
