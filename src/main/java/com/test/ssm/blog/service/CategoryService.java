package com.test.ssm.blog.service;

import com.test.ssm.blog.entity.Category;

import java.util.List;

public interface CategoryService {

    //获得分类总数
    Integer countCategory();

    //获得分类列表
    List<Category> listCategory();

    //获得分类列表
    List<Category> listCategoryWithCount();

    //根据用户ID获得分类列表
    List<Category> listCategoryWithCountByUserId(Integer categoryUserId);

    //删除分类
    void deleteCategory(Integer id);

    //根据ID查询分类信息
    Category getCategoryById(Integer id);

    //添加分类
    Category insertCategory(Category category);

    //更新
    void updateCategory(Category category);

    //根据名称查询
    Category getCategoryByName(String name);
}
