package com.fast.start.basic.web.page.mybatis;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 *
 * mybatisPlus 分页结构体
 *
 * 所有属性来源为mybatisPlus自带的分页IPage类的属性
 *
 * 主要用于转换为 PageDto 使用
 *
 * @author Snykta
 * @date 2023-09-22
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MybatisPlusPage {

    /**
     * 每页显示条数
     */
    private long size;

    /**
     * 当前页
     */
    private long current;

    /**
     * 总条数
     */
    private long total;

    /**
     * 总页数
     */
    private long pages;

    /**
     * 数据集合
     */
    private List<Object> records;

}
