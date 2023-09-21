package com.fast.start.mybatis.mapper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ClassUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.Db;

import java.util.Collection;
import java.util.List;


@SuppressWarnings("unchecked")
public interface BaseMapperPlus<T> extends BaseMapper<T> {


    default Class<T> currentModelClass() {
        return (Class<T>) ClassUtil.getTypeArgument(BaseMapperPlus.class);
    }

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
