package com.snykta.auto.code.gen.controller;

import com.snykta.starter.basic.web.web.controller.BaseController;
import com.snykta.starter.tools.web.result.Ret;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "主路由", value = "主路由")
@Controller
public class IndexController extends BaseController {

    @ApiOperation("主路由")
    @GetMapping("/")
    @ResponseBody
    public Ret<String> index() {
        return Ret.success("自动生成代码页面路由地址为：/gen-page");
    }

}
