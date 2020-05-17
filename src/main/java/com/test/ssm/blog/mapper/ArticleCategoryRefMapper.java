package com.test.ssm.blog.mapper;

import com.test.ssm.blog.entity.ArticleCategoryRef;
import com.test.ssm.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleCategoryRefMapper {

    //添加文章和分类关联记录
    int insert(ArticleCategoryRef record);

    //根据分类ID删除记录
    int deleteByCategoryId(Integer categoryId);

    //根据文章ID删除记录
    int deleteByArticleId(Integer articleId);

    //根据分类ID统计文章数
    int countArticleByCategoryId(Integer categoryId);

    //根据文章ID查询分类ID
    List<Integer> selectCategoryIdByArticleId(Integer articleId);

    //根据分类ID查询文章ID
    List<Integer> selectArticleIdByCategoryId(Integer categoryId);

    //根据文章ID获得分类列表
    List<Category> listCategoryByArticleId(Integer articleId);

}
