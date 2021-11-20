package com.yzv.consumer;

import com.yzv.ribbon.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@RibbonClient(name = "provider-dept",configuration = RibbonConfig.class)
public class SpringCloudConsumerStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumerStarter.class, args);
    }
}
