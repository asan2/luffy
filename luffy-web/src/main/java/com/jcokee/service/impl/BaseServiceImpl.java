package com.jcokee.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

public class BaseServiceImpl<T> {
    @Autowired
    private BaseMapper mapper;

    public T selectById(Long id) {
        return (T) mapper.selectByPrimaryKey(id);
    }

    public List<T> selectAll() {
        return mapper.select(null);
    }

    public T selectOne(T record) {
        return (T) mapper.selectOne(record);
    }

    public List<T> selectListByWhere(T record) {
        return mapper.select(record);
    }

    public PageInfo<T> selectPageListByWhere(T record, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<T> list = mapper.select(record);
        return new PageInfo<T>(list);
    }

    public Integer insert(T record) {
        return mapper.insert(record);
    }

    public Integer updateSelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    public Integer deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public Integer deleteByPreportys(Class<T> clazz, String preporty, Object[] values) {
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn(preporty, Arrays.asList(values));
        return mapper.delete(example);
    }

    public Integer deleteByWhere(T record) {
        return mapper.delete(record);
    }
}
