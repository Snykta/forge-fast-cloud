package com.snykta.gen.dto;


import com.snykta.tools.web.search.BaseSearchDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class SearchDto extends BaseSearchDto implements Serializable {

    /**
     * 表名称
     */
    @ApiModelProperty("表名称")
    private String tableName;

}
