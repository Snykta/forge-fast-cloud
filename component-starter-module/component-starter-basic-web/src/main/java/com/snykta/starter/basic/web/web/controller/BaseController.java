package com.snykta.starter.basic.web.web.controller;


import org.springframework.beans.factory.annotation.Value;

public class BaseController {

    @Value("${spring.profiles.active}")
    public String appProfile;



}
