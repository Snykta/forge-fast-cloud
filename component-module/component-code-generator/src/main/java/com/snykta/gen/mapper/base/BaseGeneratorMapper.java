
package com.snykta.gen.mapper.base;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snykta.gen.dto.ColumnDto;
import com.snykta.gen.dto.SearchDto;
import com.snykta.gen.dto.TableDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 数据库接口
 *
 */
public interface BaseGeneratorMapper {

    /**
     * 查询所有表
     * @param searchDto
     * @return
     */
    Page<TableDto> queryTableList(Page<TableDto> page, @Param("searchDto") SearchDto searchDto);

    /**
     * 查询表信息
     * @param tableName
     * @return
     */
    TableDto queryTable(String tableName);

    /**
     * 查询表字段
     * @param tableName
     * @return
     */
    List<ColumnDto> queryColumns(String tableName);
}
