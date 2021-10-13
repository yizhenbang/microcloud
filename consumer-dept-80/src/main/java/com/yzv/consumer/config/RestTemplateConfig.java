package com.yzv.consumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

/**
 * ClassName: RestTemplateConfig
 * Description: 这个是消费端通过该类直接访问到rest接口
 * date: 2021/10/12 18:47
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
public class RestTemplateConfig {

    @Autowired
    private ConsumerClientHTTPInterceptor consumerClientHTTPInterceptor;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Arrays.asList(this.consumerClientHTTPInterceptor));
        return restTemplate;
    }

}
