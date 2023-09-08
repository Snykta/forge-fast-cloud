package com.fast.start.basic.web.service;

import org.springframework.beans.factory.annotation.Value;

public class BaseService {

    @Value("${app.debug}")
    public Boolean appDebug;



}
