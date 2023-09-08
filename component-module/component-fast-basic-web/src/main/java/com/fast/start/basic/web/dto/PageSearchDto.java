package com.fast.start.basic.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

@ApiModel("基础分页查询父类")
@Data
public class PageSearchDto extends SearchDto implements Serializable {

    /**
     * 每页记录数
     */
    @ApiModelProperty("每页记录数")
    private int pageSize = 10;
    /**
     * 第几页
     */
    @ApiModelProperty("第几页")
    private int pageNum = 1;


}
