package com.test.ssm.blog.service;

import com.test.ssm.blog.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 获得用户列表
     */
    List<User> listUser();

    /**
     * 根据ID查询信息
     */
    User getUserById(Integer id);

    /**
     * 修改用户信息
     */
    void updateUser(User user);

    /**
     * 删除用户
     */
    void deleteUser(Integer id);

    /**
     * 添加用户
     */
    User insertUser(User user);

    /**
     * 根据用户名或邮箱查询用户
     */
    User getUserByNameOrEmail(String str);

    /**
     * 根据用户名查询用户
     */
    User getUserByName(String name);

    /**
     * 根据邮箱查询用户
     */
    User getUserByEmail(String email);

}
