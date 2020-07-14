package com.jcokee.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcokee.pojo.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public class BaseServiceImpl<T> {

    @Autowired
    private Mapper mapper;

    public int insert(T entity) {
        return mapper.insert(entity);
    }

    public int insertSelective(T entity) {
        return mapper.insertSelective(entity);
    }

    public int updateById(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    public int updateByIdSelective(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    public int updateByExample(T entity, Example example) {
        return mapper.updateByExample(entity, example);
    }

    public T selectById(Object id) {
        return (T) mapper.selectByPrimaryKey(id);
    }

    public int deleteById(Object id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public PageResult<T> selectByPage(int pageNum, int pageSize, Example example) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> records = mapper.select(example);
        PageInfo<T> pageInfo = new PageInfo<>(records);
        PageResult<T> result = new PageResult<>();
        result.setPageNum(pageInfo.getPageNum());
        result.setPages(pageInfo.getPages());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setResults(pageInfo.getList());
        return result;
    }
}
