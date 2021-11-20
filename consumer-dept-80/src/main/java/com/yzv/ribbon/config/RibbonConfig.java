package com.yzv.ribbon.config;

import com.netflix.loadbalancer.IRule;
import com.yzv.ribbon.rult.NacosWeightRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfig {
    @Bean("nacosWeightRule")
    public IRule getRule() {
        return new NacosWeightRule();
    }
}
