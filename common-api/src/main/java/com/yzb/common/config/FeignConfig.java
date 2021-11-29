package com.yzb.common.config;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignConfig {
    @Bean
    public Logger.Level getLogger(){
        return Logger.Level.FULL;
    }

    // 正常是通过消费端调用服务端，要想在sentinel设置授权信息就在请求头中追加数据
    @Bean
    public RequestInterceptor requestInterceptor(){
        return (template -> template.header("serviceName","xiaoyi"));
    }
}
