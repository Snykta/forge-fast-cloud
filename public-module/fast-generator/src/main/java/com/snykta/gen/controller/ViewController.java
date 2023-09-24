package com.snykta.gen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 生成代码的 html 页面路由
 */
@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

}
