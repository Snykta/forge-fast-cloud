package com.fast.start.system.controller;

import cn.hutool.json.JSONUtil;
import com.fast.start.basic.exception.ServiceException;
import com.fast.start.basic.utils.FastConvertUtil;
import com.fast.start.basic.utils.FastStrUtil;
import com.fast.start.basic.web.controller.BaseController;
import com.fast.start.basic.web.utils.Ret;
//import com.fast.start.system.dto.SysUserDto;
//import com.fast.start.system.service.ISysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping
public class TestController  extends BaseController {



//    @Resource
//    private ISysUserService sysUserService;
//


    @GetMapping("/hello")
    public Ret<Void> hello(){
//
//        List<SysUserDto> list = FastConvertUtil.convertToDto(sysUserService.list(), SysUserDto.class);
//        System.out.println(JSONUtil.toJsonStr(list));
        return Ret.success("hello-system");

//        return success(list);
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
