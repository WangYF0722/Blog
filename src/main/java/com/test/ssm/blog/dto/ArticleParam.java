package com.test.ssm.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * 写文章类，后面插入文章的时候进行转换
 */

@Data
public class ArticleParam {

    private Integer articleId;

    private String articleTitle;

    private String articleContent;

    private Integer articleParentCategoryId;

    private Integer articleChildCategoryId;

    private Integer articleOrder;

    private Integer articleStatus;

    private List<Integer> articleTagIds;
}
