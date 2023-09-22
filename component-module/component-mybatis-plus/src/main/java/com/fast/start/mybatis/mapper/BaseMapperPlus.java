package com.fast.start.mybatis.mapper;


import cn.hutool.core.util.ClassUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;

import java.util.Collection;
import java.util.List;



public interface BaseMapperPlus<T> extends BaseMapper<T> {


    /**
     * 查询全部
     */
    default List<T> selectList() {
        return this.selectList(new QueryWrapper<>());
    }


    /**
     * 批量插入
     */
    default boolean insertBatch(Collection<T> entityList) {
        return Db.saveBatch(entityList);
    }


    /**
     * 批量插入或更新
     */
    default boolean insertOrUpdateBatch(Collection<T> entityList) {
        return Db.saveOrUpdateBatch(entityList);
    }


    /**
     * 插入或更新(包含限制条数)
     */
    default boolean saveOrUpdate(T entity) {
        return Db.saveOrUpdate(entity);
    }




}
