package com.test.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
文章
 */
@Data
public class Article implements Serializable {
    private static final long serialVersionUID = 5207865247400761539L;
    private Integer articleId;  //ID
    private Integer articleUserId; //作者ID
    private String articleTitle;  //标题
    private Integer articleViewCount;  //查看数量
    private Integer articleCommentCount;  //评论数量
    private Integer articleLikeCount;  //点赞数量
    private Date articleCreateTime; //创建时间
    private Date articleUpdateTime;  //更新时间
    private Integer articleIsComment; //是否评论
    private Integer articleStatus; //状态
    private Integer articleOrder;  //顺序
    private String articleContent;  //内容
    private String articleSummary; //总结
    private User user;  //发布用户
    private List<Tag> tagList;  //标签列表
    private List<Category> categoryList;  //分类列表

}
