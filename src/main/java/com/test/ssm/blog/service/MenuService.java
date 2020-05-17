package com.test.ssm.blog.service;


import com.test.ssm.blog.entity.Menu;

import java.util.List;

public interface MenuService {

    //获得菜单列表
    List<Menu> listMenu();

    //添加菜单项目
    Menu insertMenu(Menu menu);

    //删除菜单项目
    void deleteMenu(Integer id);

    //更新
    void updateMenu(Menu menu);

    //根据ID获取菜单项目信息
    Menu getMenuById(Integer id);
}
