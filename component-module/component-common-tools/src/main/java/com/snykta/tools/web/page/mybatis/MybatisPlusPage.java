package com.snykta.tools.web.page.mybatis;


import lombok.Data;

import java.util.List;


/**
 *
 * 适配器
 *
 * mybatisPlus 分页结构体
 *
 * 所有属性来源为mybatisPlus自带的分页 Page 类的属性
 *
 * 主要用于转换为 PageDto 使用
 *
 * @author Snykta
 * @date 2023-09-22
 */

@Data
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


    /**
     * 总页数
     * @return
     */
    public long getPages() {
        if (getSize() == 0) {
            return 0L;
        }
        long pages = getTotal() / getSize();
        if (getTotal() % getSize() != 0) {
            pages++;
        }
        return pages;
    }


}
