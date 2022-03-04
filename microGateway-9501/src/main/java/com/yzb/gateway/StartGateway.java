package com.yzb.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;

@SpringBootApplication
@EnableDiscoveryClient
public class StartGateway {
    public static void main(String[] args) {
        SpringApplication.run(StartGateway.class,args);
    }
}