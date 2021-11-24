package com.yzb.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix // 开启 Hystrix的监控
// @EnableCircuitBreaker // 开启熔断
public class SpringCloudHystrixProviderStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudHystrixProviderStarter.class, args);
    }
}
