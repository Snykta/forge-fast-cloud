package com.snykta.gen.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 表数据
 */

@ApiModel(value = "TableDto")
@Data
public class TableDto {

    /**
     * 表名
     */
    @ApiModelProperty("表名")
    private String tableName;

    /**
     * 引擎
     */
    @ApiModelProperty("数据库引擎")
    private String engine;

    /**
     * 表备注
     */
    @ApiModelProperty("表备注")
    private String tableComment;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("创建时间")
    private Date createTime;

}
