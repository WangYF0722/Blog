package com.test.ssm.blog.service;

import com.test.ssm.blog.entity.Tag;
import javafx.scene.control.TableFocusModel;

import java.util.List;

public interface TagService {

    //获得总数
    Integer countTag();

    //获得标签列表
    List<Tag> listTag();

    //获得标签列表，及文章数目
    List<Tag> listTagWithCount();

    //根据ID
    Tag getTagById(Integer id);

    //添加
    Tag insertTag(Tag tag);

    //更新
    void updateTag(Tag tag);

    //删除
    void deleteTag(Integer id);

    //根据名称
    Tag getTagByName(String name);

    //根据文章ID获得标签
    List<Tag> listTagByArticleId(Integer articleId);

    List<Tag> listTagByUserId(Integer tagUserId);
}
