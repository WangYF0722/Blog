package com.test.ssm.blog.mapper;

import com.test.ssm.blog.entity.ArticleTagRef;
import com.test.ssm.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleTagRefMapper {

    //添加文章和标签关联记录
    int insert(ArticleTagRef record);

    //根据标签ID删除记录
    int deleteByTagId(Integer tagId);

    //根据文章ID删除记录
    int deleteByArticleId(Integer articleId);

    //根据标签ID统计文章数
    int countArticleByTagId(Integer tagId);

    //根据文章ID获得标签列表
    List<Tag> listTagByArticleId(Integer articleId);


}
