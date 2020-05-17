package com.test.ssm.blog.service;

import com.test.ssm.blog.entity.Notice;

import java.util.List;

public interface NoticeService {

    //获得公告列表
    List<Notice> listNotice(Integer status);

    //添加公告
    void insertNotice(Notice notice);

    //删除
    void deleteNotice(Integer id);

    //更新
    void updateNotice(Notice notice);

    //根据ID查询
    Notice getNoticeById(Integer id);
}
