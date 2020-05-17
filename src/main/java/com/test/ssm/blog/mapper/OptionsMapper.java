package com.test.ssm.blog.mapper;

import com.test.ssm.blog.entity.Options;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OptionsMapper {
    //根据ID删除
    int deleteById(Integer optionId);

    //添加
    int insert(Options options);

    //根据ID查询
    Options getOptionsById(Integer optionId);

    //更新
    int update(Options options);

    //获得记录
    Options getOptions();
}
