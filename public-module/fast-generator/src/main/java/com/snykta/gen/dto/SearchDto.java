package com.snykta.gen.dto;


import com.snykta.tools.web.search.BaseSearchDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class SearchDto extends BaseSearchDto implements Serializable {

    /**
     * 表名称
     */
    private String tableName;

}
