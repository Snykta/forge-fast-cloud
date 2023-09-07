package com.fast.start.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageDto<T> implements Serializable {

    @ApiModelProperty("分页对象")
    public List<T> data;

    /**
     * 每页记录数
     */
    @ApiModelProperty("每页记录数")
    private int pageSize;

    /**
     * 当前第几页
     */
    @ApiModelProperty("当前所在页码")
    private int pageNum;

    /**
     * 总记录数
     */
    @ApiModelProperty("总记录数")
    private long totalCount;

    /**
     * 总页数
     */
    @ApiModelProperty("总页码")
    private int totalPage;

}
