package com.snykta.mybatis.mapper;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import java.util.Collection;
import java.util.List;


/**
 * 所有自定义 mapper 都继承此 mapper 然后进行crud操作
 * service层禁止继承或者实现 mybatisPlus自带的crud的Service基类 (！！禁止禁止禁止！！)
 * 所有操作数据库的动作全部下放到 mapper 层，service 层只需关系实际的业务
 *
 * @param <T> 单表实体类
 */
public interface BaseMapperPlus<T> extends BaseMapper<T> {


    /**
     * 单表查询全部
     */
    default List<T> selectList() {
        return this.selectList(new QueryWrapper<>());
    }


    /**
     * 单表批量插入
     */
    default boolean insertBatch(Collection<T> entityList) {
        return Db.saveBatch(entityList);
    }


    /**
     * 单表批量插入或更新
     */
    default boolean insertOrUpdateBatch(Collection<T> entityList) {
        return Db.saveOrUpdateBatch(entityList);
    }


    /**
     * 单表插入或更新(包含限制条数)
     */
    default boolean saveOrUpdate(T entity) {
        return Db.saveOrUpdate(entity);
    }




}
