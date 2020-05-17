package com.test.ssm.blog.mapper;

import com.test.ssm.blog.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {

    //根据ID删除
    int deleteById(Integer noticeId);

    //添加
    int insert(Notice notice);

    //根据ID查询

    Notice getNoticeById(Integer noticeId);

    //更新
    int update(Notice notice);

    //根据主键更新公告
    int updateByPrimaryKey(Notice notice);

    //获得公告总数
    Integer countNotice(@Param(value = "status")Integer status);

    //获得公告列表
    List<Notice> listNotice(@Param(value = "status")Integer status);


}
