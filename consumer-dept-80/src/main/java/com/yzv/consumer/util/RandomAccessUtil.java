package com.yzv.consumer.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RandomAccessUtil {
    @Autowired
    private DiscoveryClient discoveryClient;

    public String getTargetUrl(String serverName, String url) {
        //通过名字获取所有的服务实例
        List<ServiceInstance> instances = discoveryClient.getInstances(serverName);
        log.info("实例列表：{}",instances);
        if (instances.size() == 0) {
            throw new RuntimeException("该服务名下没有Nacos实例信息");
        }
        List<String> collect = instances.stream().map(instance -> instance.getUri().toString()+url).collect(Collectors.toList());
        int index = ThreadLocalRandom.current().nextInt(collect.size());
        String targetUrl = collect.get(index);
        log.info("获取到nacos服务地址：{}", targetUrl);
        return targetUrl;
    }
}
