package com.snykta.mybatis.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 自定义生成分页对象构造工具类
 *
 * Snykta
 *
 * @param <T>
 */
public class PageRequest<T> extends Page<T> {


    public PageRequest(long current, long size, long total, boolean searchCount) {
        super(current, size, total, searchCount);
        setOptimizeJoinOfCountSql(false);
        setOptimizeCountSql(false);
    }

    /**
     *
     * @param current 当前页数
     * @param size 每页显示条数
     * @param <T>
     * @return
     */
    public static <T> Page<T> of(long current, long size) {
        return of(current, size, 0);
    }


    /**
     *
     * @param current 当前页数
     * @param size 每页显示条数
     * @param total 设置当前满足条件总行数
     * @param <T>
     * @return
     */
    public static <T> Page<T> of(long current, long size, long total) {
        return of(current, size, total, true);
    }

    /**
     *
     * @param current 当前页数
     * @param size 每页显示条数
     * @param searchCount 是否进行总条数查询，默认true 如果使用的是原生的Pagination 对象则设置为 false
     * @param <T>
     * @return
     */
    public static <T> Page<T> of(long current, long size, boolean searchCount) {
        return of(current, size, 0, searchCount);
    }

    /**
     *
     * @param current 当前页数
     * @param size 每页显示条数
     * @param total 设置当前满足条件总行数
     * @param searchCount 是否进行总条数查询，默认true 如果使用的是原生的Pagination 对象则设置为 false
     * @param <T>
     * @return
     */
    public static <T> Page<T> of(long current, long size, long total, boolean searchCount) {
        return new PageRequest<T>(current, size, total, searchCount);
    }



}
