package com.snykta.starter.tools.web.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 由于各家orm框架的分页结构体不一致因此做了统一
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
    @ApiModelProperty("数据")
    public List<T> data;

    /**
     * 每页记录数
     */
    @ApiModelProperty("每页记录数")
    private long pageSize;

    /**
     * 当前第几页
     */
    @ApiModelProperty("当前第几页")
    private long pageNum;

    /**
     * 总记录数
     */
    @ApiModelProperty("总记录数")
    private long totalCount;

    /**
     * 总页数
     */
    @ApiModelProperty("总页数")
    private long totalPage;


}