package com.test.ssm.blog.service.impl;

import com.test.ssm.blog.entity.Category;
import com.test.ssm.blog.mapper.ArticleCategoryRefMapper;
import com.test.ssm.blog.mapper.CategoryMapper;
import com.test.ssm.blog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired(required = false)
    CategoryMapper categoryMapper;

    @Autowired(required = false)
    ArticleCategoryRefMapper articleCategoryRefMapper;

    @Override
    public Integer countCategory() {
        Integer count = 0;
        try {
            count = categoryMapper.countCategory();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("统计分类失败, cause:{}", e);
        }
        return count;
    }

    @Override
    public List<Category> listCategory() {
        List<Category> categoryList = null;
        try {
            categoryList = categoryMapper.listCategory();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据文章获得分类列表失败, cause:{}", e);
        }
        return categoryList;
    }

    //获得分类列表的时候，设置了和这个分类有关的文章数
    @Override
    public List<Category> listCategoryWithCount() {
       List<Category> categoryList=null;
       try{
           categoryList=categoryMapper.listCategory();
           for(int i=0;i<categoryList.size();i++)
           {
               //根据分类的ID去查找这个分类下的文章数
               Integer count=articleCategoryRefMapper.countArticleByCategoryId(categoryList.get(i).getCategoryId());
               categoryList.get(i).setArticleCount(count);
           }
       }catch (Exception e) {
           e.printStackTrace();
           log.error("根据文章获得分类列表失败, cause:{}", e);
       }
        return categoryList;
    }

    @Override
    public List<Category> listCategoryWithCountByUserId(Integer categoryUserId) {
        List<Category> categoryList=null;
        try
        {
            categoryList=categoryMapper.listCategoryByUserId(categoryUserId);
            for(int i=0;i<categoryList.size();i++){
                Integer count=articleCategoryRefMapper.countArticleByCategoryId(categoryList.get(i).getCategoryId());
                categoryList.get(i).setArticleCount(count);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return categoryList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCategory(Integer id) {
        try{
            //因为文章和分类有关，所以在分类中删除，也要在文章和分类关联的表里删除
            categoryMapper.deleteCategory(id);
            articleCategoryRefMapper.deleteByCategoryId(id);
        }catch (Exception e){
            e.printStackTrace();
            log.error("删除分类失败, id:{}, cause:{}", id, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryMapper.getCategoryById(id);
    }

    @Override
    public Category insertCategory(Category category) {
        categoryMapper.insert(category);
        return category;
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        Category category = null;
        try {
            category = categoryMapper.getCategoryByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新分类失败, category:{}, cause:{}", category, e);
        }
        return category;
    }
}
