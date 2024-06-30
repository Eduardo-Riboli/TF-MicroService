package com.engsoft2.cache_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CacheServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheServiceApplication.class, args);
	}
}
