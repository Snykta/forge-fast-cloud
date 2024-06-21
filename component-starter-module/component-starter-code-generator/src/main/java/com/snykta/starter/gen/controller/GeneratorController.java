package com.snykta.starter.gen.controller;

import cn.hutool.core.io.IoUtil;
import com.snykta.starter.basic.web.web.controller.BaseController;
import com.snykta.starter.gen.dto.SearchDto;
import com.snykta.starter.gen.dto.TableDto;
import com.snykta.starter.gen.service.GeneratorService;
import com.snykta.starter.tools.utils.CyStrUtil;
import com.snykta.starter.tools.web.page.PageDto;
import com.snykta.starter.tools.web.result.Ret;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 代码生成器
 *
 */
@Api(tags = "自动生成代码", value = "自动生成代码")
@Controller
@RequestMapping("gen")
@ConditionalOnProperty(name = "gen.code.config.enable", havingValue = "true")
public class GeneratorController extends BaseController {


    private final GeneratorService sysGeneratorService;

    public GeneratorController(GeneratorService sysGeneratorService) {
        this.sysGeneratorService = sysGeneratorService;
    }

    /**
     * 分页
     */
    @ApiOperation("分页查询数据表")
    @ResponseBody
    @PostMapping("/queryPage")
    public Ret<PageDto<TableDto>> queryPage(@RequestBody SearchDto searchDto) {
        return Ret.success(sysGeneratorService.queryPage(searchDto));
    }


    /**
     * 生成代码压缩包
     */
    @ApiOperation("生成代码压缩包")
    @GetMapping("/code")
    public void code(@RequestParam("tables") String tables,@RequestParam("packName") String packName, HttpServletResponse response) throws Exception {
        byte[] data = sysGeneratorService.generatorCode(CyStrUtil.splitToArray(tables, ","), packName);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"GenCode.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IoUtil.write(response.getOutputStream(), false, data);
    }


}
