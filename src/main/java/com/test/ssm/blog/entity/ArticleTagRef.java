package com.test.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/*
文章对标签的引用
 */
@Data
public class ArticleTagRef implements Serializable {
    private static final long serialVersionUID = -5816783232020910492L;
    private Integer articleId; //文章ID
    private Integer tagId;   //标签ID
    public ArticleTagRef(){

    }

    public ArticleTagRef(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }
}
