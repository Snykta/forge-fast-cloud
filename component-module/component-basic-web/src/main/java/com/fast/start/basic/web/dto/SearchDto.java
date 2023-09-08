package com.fast.start.basic.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("基础查询父类")
@Data
public class SearchDto implements Serializable {

    @ApiModelProperty("关键字")
    private String keyword;
}
