package com.yzb.common.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class FeignConfig {
    @Bean
    public Logger.Level getLogger(){
        return Logger.Level.FULL;
    }
}
