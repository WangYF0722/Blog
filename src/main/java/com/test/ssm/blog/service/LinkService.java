package com.test.ssm.blog.service;

import com.test.ssm.blog.entity.Link;

import java.util.List;

public interface LinkService {

    //获得链接总数
    Integer countLink(Integer status);

    //获得链接列表
    List<Link> listLink(Integer status);

    //添加
    void insertLink(Link link);

    //删除
    void deleteLink(Integer id);

    //更新
    void updateLink(Link link);

    //根据ID查询
    Link getLinkById(Integer id);

}
