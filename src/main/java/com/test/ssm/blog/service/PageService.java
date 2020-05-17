package com.test.ssm.blog.service;

import com.test.ssm.blog.entity.Page;

import java.util.List;

public interface PageService {

    //获得页面列表
    List<Page> listPage(Integer status);

    //根据页面key获得页面
    Page getPageByKey(Integer status,String key);

    //根据ID获取页面
    Page getPageById(Integer id);

    //添加页面
    void insertPage(Page page);

    //删除页面
    void deletePage(Integer id);

    //编辑页面
    void updatePage(Page page);
}
