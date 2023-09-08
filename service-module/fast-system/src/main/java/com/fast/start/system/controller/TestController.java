package com.fast.start.system.controller;

import com.fast.start.basic.exception.ServiceException;
import com.fast.start.basic.utils.FastStrUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello-system";
    }


    @GetMapping("/hello2")
    public String hello2(){
        System.out.println(1/0);
        return "hello-system";
    }

    @GetMapping("/hello3")
    public String hello3(String aa){

        if (FastStrUtil.isEmpty(aa)) {
            throw new ServiceException("参数不能为空");
        }
        System.out.println(1/0);
        return "hello-system";
    }


    @PostMapping("/hello5")
    public String hello5(String aa){

        if (FastStrUtil.isEmpty(aa)) {
            throw new ServiceException("参数不能为空");
        }
        System.out.println(1/0);
        return "hello-system";
    }


    @GetMapping("/hello6")
    public String hello6(String aa){

        System.out.println(aa.split(","));


        if (FastStrUtil.isEmpty(aa)) {
            throw new ServiceException("参数不能为空");
        }
        System.out.println(1/0);
        return "hello-system";
    }

}
