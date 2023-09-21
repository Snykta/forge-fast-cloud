package com.fast.start.mybatis.page;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

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