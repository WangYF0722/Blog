package com.test.ssm.blog.mapper;

import com.test.ssm.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {

    //根据ID删除
    int deleteById(Integer tagId);

    //添加
    int insert(Tag tag);

    //根据ID查询
    Tag getTagById(Integer tagId);

    //更新
    int update(Tag tag);

    //获得标签总数
    int countTag();

    //获得标签列表
    List<Tag> listTag();

    //根据用户ID获得标签列表
    List<Tag> listTagByUserId(Integer tagUserId);

    //根据标签名获取标签
    Tag getTagByName(String name);
}
