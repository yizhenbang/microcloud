package com.yzv.consumer;

import com.yzv.ribbon.config.RibbonConfig;
import feign.Feign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@RibbonClient(name = "provider-dept",configuration = RibbonConfig.class)
@EnableFeignClients("com.yzb.common.service")
@ComponentScan({"com.yzb.common.service","com.yzb.common.hystrix","com.yzv.consumer"})
public class SpringCloudConsumerStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumerStarter.class, args);
    }
}
