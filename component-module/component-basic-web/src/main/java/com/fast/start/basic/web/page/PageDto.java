package com.fast.start.basic.web.page;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 统一返回分页结构体
 *
 * 例如：
 * mybatisPlusPage -> PageDto
 *
 * springDataJpaPage -> PageDto
 *
 * @author Snykta
 * @date 2023-09-22
 */
@Builder
@Data
public class PageDto<T> implements Serializable {

    /**
     * 数据
     */
    public List<T> data;

    /**
     * 每页记录数
     */
    private Long pageSize;

    /**
     * 当前第几页
     */
    private Long pageNum;

    /**
     * 总记录数
     */
    private Long totalCount;

    /**
     * 总页数
     */
    private Long totalPage;


}