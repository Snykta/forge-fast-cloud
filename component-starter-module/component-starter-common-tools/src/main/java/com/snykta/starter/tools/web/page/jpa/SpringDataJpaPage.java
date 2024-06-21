package com.snykta.starter.tools.web.page.jpa;


import lombok.Data;

import java.util.List;

/**
 *
 * 适配器
 *
 * SpringDataJpa 分页结构体
 *
 * 所有属性来源为 springDataJpa 自带的分页 Page 类的属性
 *
 * 主要用于转换为 PageDto 使用
 *
 * @author Snykta
 * @date 2023-09-22
 */

@Data
public class SpringDataJpaPage {

    /**
     * 每页数量
     */
    private int size;
    /**
     * 当前第几页
     */
    private int number;
    /**
     * 总条数
     */
    private int totalElements;
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 数据集合
     */
    private List<Object> content;


}
