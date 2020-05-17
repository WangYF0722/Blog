package com.test.ssm.blog.service;

import com.test.ssm.blog.entity.Options;

public interface OptionsService {

    //获得基本信息
    Options getOptions();

    //新建基本信息
    void insertOptions(Options options);

    //更新基本信息
    void updateOptions(Options options);
}
