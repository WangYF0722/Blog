package com.test.ssm.blog.mapper;

import com.test.ssm.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    //添加分类
    int insert(Category category);

    //更新
    int update(Category category);

    //根据分类ID获得分类信息
    Category getCategoryById(Integer id);

    //删除分类
    int deleteCategory(Integer id);

    //查询分类总数
    Integer countCategory();

    //获得分类列表
    List<Category> listCategory();

    //根据用户ID获得分类列表
    List<Category> listCategoryByUserId(Integer categoryUserId);

    //根据父分类查找子分类
    List<Category> findChildCategory(@Param(value = "id")Integer id);

    //根据标签名获取标签
    Category getCategoryByName(String name);
}
