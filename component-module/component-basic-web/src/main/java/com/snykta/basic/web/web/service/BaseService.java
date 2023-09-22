package com.snykta.basic.web.web.service;

import org.springframework.beans.factory.annotation.Value;

public class BaseService {

    @Value("${app.profile}")
    public String appProfile;



}
