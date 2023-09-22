
package com.fast.start.gen.controller;

import com.fast.start.basic.web.page.PageDto;
import com.fast.start.basic.web.utils.Ret;
import com.fast.start.gen.dto.SearchDto;
import com.fast.start.gen.dto.TableDto;
import com.fast.start.gen.service.GeneratorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 代码生成器
 *
 */
@Controller
@RequestMapping("/gen")
public class GeneratorController {


    private final GeneratorService sysGeneratorService;

    public GeneratorController(GeneratorService sysGeneratorService) {
        this.sysGeneratorService = sysGeneratorService;
    }

    /**
     * 分页
     */
    @ResponseBody
    @PostMapping("/queryPage")
    public Ret<PageDto<TableDto>> queryPage(@RequestBody SearchDto searchDto) {
        return Ret.success(sysGeneratorService.queryPage(searchDto));
    }

//    /**
//     * 生成代码
//     */
//    @RequestMapping("/code")
//    public void code(String tables, HttpServletResponse response) throws IOException {
//        byte[] data = sysGeneratorService.generatorCode(tables.split(","));
//
//        response.reset();
//        response.setHeader("Content-Disposition", "attachment; filename=\"renren.zip\"");
//        response.addHeader("Content-Length", "" + data.length);
//        response.setContentType("application/octet-stream; charset=UTF-8");
//
//        IoUtil.write(response.getOutputStream(), false, data);
//    }
}
