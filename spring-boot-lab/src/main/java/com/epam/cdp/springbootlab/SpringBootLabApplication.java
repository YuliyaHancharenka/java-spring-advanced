package com.epam.cdp.springbootlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients
@SpringBootApplication
public class SpringBootLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLabApplication.class, args);
	}

}
