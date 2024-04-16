package com.snykta.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class FastAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastAuthApplication.class, args);
    }

}
