package com.snykta.starter.basic.web.web.service;

import org.springframework.beans.factory.annotation.Value;

public class BaseService {

    @Value("${spring.profiles.active}")
    public String appProfile;



}
