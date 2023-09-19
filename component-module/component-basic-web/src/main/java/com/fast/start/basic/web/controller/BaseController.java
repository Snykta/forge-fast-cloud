package com.fast.start.basic.web.controller;


import org.springframework.beans.factory.annotation.Value;

public class BaseController {

    @Value("${app.profile}")
    public String appProfile;



}
