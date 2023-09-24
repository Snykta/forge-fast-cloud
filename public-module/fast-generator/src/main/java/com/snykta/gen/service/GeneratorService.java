package com.snykta.gen.service;



import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snykta.basic.web.exception.ServiceException;
import com.snykta.basic.web.utils.CyReUtil;
import com.snykta.basic.web.utils.FastConvertUtil;
import com.snykta.basic.web.utils.FastObjUtil;
import com.snykta.basic.web.web.page.PageDto;
import com.snykta.gen.dto.ColumnDto;
import com.snykta.gen.dto.TableDto;
import com.snykta.gen.mapper.base.BaseGeneratorMapper;
import com.snykta.gen.dto.SearchDto;
import com.snykta.gen.utils.GenUtils;
import com.snykta.mybatis.page.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    private BaseGeneratorMapper baseGeneratorMapper;

    /**
     * 分页查询表
     * @param searchDto
     * @return
     */
    public PageDto<TableDto> queryPage(SearchDto searchDto) {
        Page<TableDto> queryPage = baseGeneratorMapper.queryTableList(PageRequest.of(searchDto.getPageNum(), searchDto.getPageSize()), searchDto);
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
            if (FastObjUtil.isNull(table)) {
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
