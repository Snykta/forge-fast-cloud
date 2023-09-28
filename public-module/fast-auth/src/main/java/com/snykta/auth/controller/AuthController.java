package com.snykta.auth.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.snykta.auth.service.IAuthService;
import com.snykta.basic.web.web.controller.BaseController;
import com.snykta.tools.web.result.Ret;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;


@RestController
@RequestMapping(value = "auth")
@Slf4j
public class AuthController extends BaseController {

    private final IAuthService authService;
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @Resource
    private RestTemplate restTemplate;

    /**
     * 用户登录并且返回token
     * @param phoneNumber
     * @param password
     * @return
     */
    @PostMapping(value = "/doLogin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Ret<String> doLogin(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String password) {
        return Ret.success(authService.doLogin(phoneNumber, password));
    }




    @GetMapping("/cc")
    public Ret<String> cc(String token) {

        System.out.println(StpUtil.getTokenValue());
        System.out.println(StpUtil.getTokenInfo());
        System.out.println(StpUtil.getTokenSession());
        return Ret.success();
    }

}
