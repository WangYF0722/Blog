package com.test.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleCategoryRef implements Serializable {
    private static final long serialVersionUID = -6809206515467725995L;
    private Integer articleId;  //文章ID
    private Integer categoryId;  //分类ID

    public ArticleCategoryRef(Integer articleId, Integer categoryId) {
        this.articleId = articleId;
        this.categoryId = categoryId;
    }

    public ArticleCategoryRef() {
    }
}
