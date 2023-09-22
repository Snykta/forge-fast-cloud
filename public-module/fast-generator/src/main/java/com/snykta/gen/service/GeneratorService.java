package com.snykta.gen.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snykta.basic.web.utils.FastConvertUtil;
import com.snykta.basic.web.web.page.PageDto;
import com.snykta.gen.dto.ColumnDto;
import com.snykta.gen.dto.TableDto;
import com.snykta.gen.mapper.base.BaseGeneratorMapper;
import com.snykta.gen.dto.SearchDto;
import com.snykta.mybatis.page.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 代码生成器
 *
 */
@Service
public class GeneratorService {
    @Resource
    private BaseGeneratorMapper baseGeneratorMapper;

    /**
     * 分页查询表
     * @param searchDto
     * @return
     */
    public PageDto<TableDto> queryPage(SearchDto searchDto) {
        Page<TableDto> queryPage = baseGeneratorMapper.queryTableList(PageRequest.of(searchDto.getPageNum(), searchDto.getPageSize()), searchDto);
        System.out.println(queryPage.getPages());
        return FastConvertUtil.toPageDto(queryPage, TableDto.class);
    }

    /**
     * 表信息
     * @param tableName
     * @return
     */
    public TableDto queryTable(String tableName) {
        return baseGeneratorMapper.queryTable(tableName);
    }

    /**
     * 表的字段
     * @param tableName
     * @return
     */
    public List<ColumnDto> queryColumns(String tableName) {
        return baseGeneratorMapper.queryColumns(tableName);
    }



//    public byte[] generatorCode(String[] tableNames) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        ZipOutputStream zip = new ZipOutputStream(outputStream);
//
//        for (String tableName : tableNames) {
//            //查询表信息
//            TableDto table = queryTable(tableName);
//            //查询列信息
//            List<ColumnDto> columns = queryColumns(tableName);
//            //生成代码
//            GenUtils.generatorCode(table, columns, zip);
//        }
//        IOUtils.closeQuietly(zip);
//        return outputStream.toByteArray();
//    }


}
