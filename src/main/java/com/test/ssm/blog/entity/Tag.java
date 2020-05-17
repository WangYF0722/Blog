package com.test.ssm.blog.entity;


import lombok.Data;

import java.io.Serializable;

/*
标签
 */
@Data
public class Tag implements Serializable {
    private static final long serialVersionUID = 605449151900057035L;
    private Integer tagId;     //标签ID
    private String tagName;   //标签名称
    private String tagDescription;  //标签描述
    private Integer tagUserId;  //标签属于哪个用户
    private Integer articleCount;   //文章数量，不是数据库字段

    public Tag(){

    }

    public Tag(Integer tagId){
        this.tagId=tagId;
    }

    public Tag(Integer tagId, String tagName, String tagDescription, Integer tagUserId, Integer articleCount) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagDescription = tagDescription;
        this.tagUserId = tagUserId;
        this.articleCount = articleCount;
    }



}
