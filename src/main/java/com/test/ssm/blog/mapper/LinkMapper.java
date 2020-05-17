package com.test.ssm.blog.mapper;

import com.test.ssm.blog.entity.Link;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LinkMapper {

    //删除
    int deleteById(Integer linkId);

    //添加
    int insert(Link link);

    //根据ID查询
    Link getLinkById(Integer linkId);

    //更新
    int update(Link link);

    //获得链接总数
    Integer countLink(@Param(value = "status")Integer status);

    //获得链表列表
    List<Link> listLink(@Param(value = "status")Integer status);
}
