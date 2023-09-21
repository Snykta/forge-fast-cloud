//package com.fast.start.gen.controller;
//
//import cn.hutool.core.io.IoUtil;
//import com.fast.start.basic.web.utils.Ret;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Map;
//
///**
// * 代码生成器
// *
// */
//@Controller
//@RequestMapping("/gen")
//public class GeneratorController {
////    @Resource
////    private SysGeneratorService sysGeneratorService;
//
//    /**
//     * 列表
//     */
//    @ResponseBody
//    @RequestMapping("/list")
//    public Ret<Void> list(@RequestParam Map<String, Object> params) {
//        PageUtils pageUtil = sysGeneratorService.queryList(new Query(params));
//
//        return R.ok().put("page", pageUtil);
//    }
//
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
//}
//
