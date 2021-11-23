package com.yzb.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
// @EnableCircuitBreaker // 开启熔断
public class SpringCloudHystrixProviderStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudHystrixProviderStarter.class, args);
    }
}
