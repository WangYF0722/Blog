package com.test.ssm.blog.mapper;


import com.test.ssm.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 根据用户ID删除
     */
    int deleteById(Integer userId);

    /**
     * 添加用户
     */
    int insert(User user);

    /**
     * 根据ID查询
     */
    User getUserById(Integer userId);

    /**
     * 更新用户信息
     */
    int update(User user);

    /**
     * 获得用户列表
     */
    List<User> listUser();

    /**
     * 根据用户名或Email获得用户
     */
    User getUserByNameOrEmail(String str) ;

    /**
     *根据用户名查询
     */
    User getUserByName(String name);

    /**
     * 根据Email查询
     */

    User getUserByEmail(String email);
    
}
