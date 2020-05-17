package com.test.ssm.blog.service.impl;

import com.test.ssm.blog.entity.Options;
import com.test.ssm.blog.mapper.OptionsMapper;
import com.test.ssm.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class OptionsServiceImpl implements OptionsService {

    @Autowired(required = false)
    OptionsMapper optionsMapper;

    @Override
    @Cacheable(value = "default", key = "'options'")
    public Options getOptions() {
        return optionsMapper.getOptions();
    }

    /**
     * @Cacheable : 主要针对方法配置，能够根据方法的请求参数对结果进行缓存
     * 参数值：
     *         value 缓存的名称
     *         key 缓存的key(如果指定要按照SpEL表达式编写，如果不指定，则缺省按照方法的所有参数进行组合
     *         condition 缓存的条件，可以为空
     *  加这个注解的意思，当调用这个方法时，会从一个名叫value值的缓存中查询，如果没有，则执行实际方法（即查询数据库），并将执行结果保存在缓存中
     *                   否则返回缓存中的对象
     * @param options
     */
    @Override
    @Cacheable(value = "default", key = "'options'")
    public void insertOptions(Options options) {
        optionsMapper.insert(options);
    }

    @Override
    @Cacheable(value = "default", key = "'options'")
    public void updateOptions(Options options) {
        optionsMapper.update(options);
    }
}
