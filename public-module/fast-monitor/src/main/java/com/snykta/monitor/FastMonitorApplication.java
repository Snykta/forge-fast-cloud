package com.snykta.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@EnableAdminServer
public class FastMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastMonitorApplication.class, args);
    }

}
