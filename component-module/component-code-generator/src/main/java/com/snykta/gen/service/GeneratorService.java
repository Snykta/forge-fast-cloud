package com.snykta.gen.service;


import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snykta.gen.dto.ColumnDto;
import com.snykta.gen.dto.SearchDto;
import com.snykta.gen.dto.TableDto;
import com.snykta.gen.mapper.base.BaseGeneratorMapper;
import com.snykta.gen.utils.GenUtils;
import com.snykta.mybatis.page.PageRequest;
import com.snykta.tools.exception.ServiceException;
import com.snykta.tools.utils.CyConvertUtil;
import com.snykta.tools.utils.CyObjUtil;
import com.snykta.tools.utils.CyReUtil;
import com.snykta.tools.web.page.PageDto;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 *
 * 代码生成器
 *
 */
@Service
public class GeneratorService {

    private final BaseGeneratorMapper baseGeneratorMapper;

    public GeneratorService(BaseGeneratorMapper baseGeneratorMapper) {
        this.baseGeneratorMapper = baseGeneratorMapper;
    }

    /**
     * 分页查询表
     * @param searchDto
     * @return
     */
    public PageDto<TableDto> queryPage(SearchDto searchDto) {
        Page<TableDto> queryPage = baseGeneratorMapper.queryTableList(PageRequest.of(searchDto.getPageNum(), searchDto.getPageSize()), searchDto);
        return CyConvertUtil.toPageDto(queryPage, TableDto.class);
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


    /**
     * 正式生成代码压缩包
     * @param tableNames
     * @return
     */
    public byte[] generatorCode(String[] tableNames, String packName) {
        if (tableNames.length <= 0) {
            throw new ServiceException("请输入表名");
        }
        if (!CyReUtil.isValidPackageName(packName)) {
            throw new ServiceException("输入的包名不合法");
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for (String tableName : tableNames) {
            //查询表信息
            TableDto table = queryTable(tableName);
            if (CyObjUtil.isNull(table)) {
                throw new ServiceException(String.format("表名：[%s] 不存在", tableName));
            }
            //查询列信息
            List<ColumnDto> columns = queryColumns(tableName);
            //生成代码
            GenUtils.generatorCode(table, columns, zip, packName);
        }
        IoUtil.close(zip);
        return outputStream.toByteArray();
    }


}
