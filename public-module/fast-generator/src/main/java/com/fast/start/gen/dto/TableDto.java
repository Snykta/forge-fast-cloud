package com.fast.start.gen.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

/**
 * 表数据
 */

@Data
public class TableDto {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 引擎
     */
    private String engine;

    /**
     * 表备注
     */
    private String tableComment;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

}
