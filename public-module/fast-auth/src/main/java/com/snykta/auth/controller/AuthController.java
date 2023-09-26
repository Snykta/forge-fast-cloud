package com.snykta.auth.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.snykta.auth.service.IAuthService;
import com.snykta.basic.web.web.controller.BaseController;
import com.snykta.tools.web.result.Ret;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "auth")
@Slf4j
public class AuthController extends BaseController {

    private final IAuthService authService;
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/doLogin")
    public Ret<String> doLogin(@RequestParam(value = "userNumber") String userNumber, @RequestParam(value = "passWord") String passWord) {
        StpUtil.login(userNumber);

        return Ret.success(StpUtil.getTokenValue());
    }




    @GetMapping("/cc")
    public Ret<String> cc(String token) {

        System.out.println(StpUtil.getTokenValue());
        System.out.println(StpUtil.getTokenInfo());
        System.out.println(StpUtil.getTokenSession());
        return Ret.success();
    }

}
