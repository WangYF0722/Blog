package com.test.ssm.blog.mapper;

import com.test.ssm.blog.entity.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PageMapper {

    //根据ID删除
    int deleteById(Integer pageId);

    //添加
    int insert(Page page);

    //根据ID查询
    Page getPageById(Integer pageId);

    //更新
    int update(Page page);

    //获得页面列表
    List<Page> listPage(@Param(value = "status")Integer status);

    //根据key获得页面
    Page getPageByKey(@Param(value = "status")Integer status,
                      @Param(value = "key")String key);
}
