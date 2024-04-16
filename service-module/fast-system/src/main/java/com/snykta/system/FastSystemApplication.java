package com.snykta.system;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class FastSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastSystemApplication.class, args);
	}

}
