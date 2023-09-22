package com.snykta.basic.web.web.controller;


import org.springframework.beans.factory.annotation.Value;

public class BaseController {

    @Value("${app.profile}")
    public String appProfile;



}
