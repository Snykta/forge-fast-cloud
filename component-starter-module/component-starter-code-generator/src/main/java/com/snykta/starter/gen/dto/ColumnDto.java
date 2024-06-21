package com.snykta.starter.gen.dto;

import lombok.Data;

/**
 * 字段
 */

@Data
public class ColumnDto {

    private String columnName;

    private String dataType;

    private String columnComment;

    private String columnKey;

    private String extra;


}
