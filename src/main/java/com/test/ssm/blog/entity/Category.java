package com.test.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/*
文章分类
 */
@Data
public class Category implements Serializable {
    private static final long serialVersionUID = 6687286913317513141L;
    private Integer categoryId;  //ID
    private Integer categoryPid; //分类的父ID
    private String categoryName;  //名称
    private String categoryDescription;  //描述
    private Integer categoryOrder; //优先顺序
    private String categoryIcon;  //图像
    private Integer articleCount;  //文章数量
    private Integer categoryUserId;  //分类属于哪个用户

    public Category(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Category(Integer categoryId, Integer categoryPid, String categoryName, String categoryDescription, Integer categoryOrder, String categoryIcon, Integer articleCount, Integer categoryUserId) {
        this.categoryId = categoryId;
        this.categoryPid = categoryPid;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryOrder = categoryOrder;
        this.categoryIcon = categoryIcon;
        this.articleCount = articleCount;
        this.categoryUserId = categoryUserId;
    }



    public Category(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /*
    未分类
     */
    public static  Category Default(){
        return  new Category(100000000,"未分类");
    }
}
