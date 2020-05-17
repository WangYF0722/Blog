package com.test.ssm.blog.mapper;

import com.test.ssm.blog.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MenuMapper {

    //删除
    int deleteById(Integer menuId);

    //添加
    int insert(Menu menu);

    //根据ID查询
    Menu getMenuById(Integer menuId);

    //更新
    int update(Menu menu);

    //获得菜单列表
    List<Menu> listMenu();
}
