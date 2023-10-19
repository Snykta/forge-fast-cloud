package com.snykta.system.dto;

import com.snykta.tools.web.search.BaseSearchDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户查询条件Dto
 */
@Data
public class SearchUserDto extends BaseSearchDto {

    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    private String userName;


}
