package com.yzb.provider.config.block;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)// CGLIB 代理类
public class SentinelAOPConfig {
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();// 这个源码之中就是一个切面，这个切面的处理之中就是拦截了一个注解 @ SentinelResource
    }
}
