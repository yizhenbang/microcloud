package com.yzb.gateway.listening;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzb.gateway.entity.DynicRouteProperties;
import com.yzb.gateway.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * @author ZhenBang-Yi
 * @ClassName DynicNacosRouteConfig
 * @date 2022/7/31 20:04
 */
@Slf4j
@Component
@Configuration
public class DynicNacosRouteConfig implements CommandLineRunner {
    @Autowired
    private DynicRouteProperties dynicRouteProperties;
    @Autowired
    private RouteService routeService;
    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public void listeningNacosConfig() throws NacosException {
        Properties properties = this.dynicRouteProperties.getProperties();
        ConfigService configService = NacosFactory.createConfigService(properties);
        // 获取数据
        String config = configService.getConfig(this.dynicRouteProperties.getDataId(), this.dynicRouteProperties.getGroup(), this.dynicRouteProperties.getTimeOut());
        log.info("获取到Gateway的配置数据：{}", config);
        this.routesUpdation(config);
        configService.addListener(this.dynicRouteProperties.getDataId(), this.dynicRouteProperties.getGroup(), new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String content) {
                log.info("读取到更新的路由数据,{}", content);
                DynicNacosRouteConfig.this.routesUpdation(content);
            }
        });
    }

    @Override
    public void run(String... args) throws Exception {
        this.listeningNacosConfig();
    }

    /**
     * 根据gateway配置的额数据，更新理由
     *
     * @param content 接受到的gateway配置数据
     * @author: ZhenBang-Yi
     * @date 2022/7/31 20:27
     **/
    public boolean routesUpdation(String content) {
        try {
            RouteDefinition[] routeDefinitions = this.objectMapper.readValue(content, RouteDefinition[].class);

            // 获取存在当前项目中所有的路由ID
            ArrayList<List<String>> routes = this.routeService.getRoutes();
            List<String> collect = routes.stream().flatMap(e -> e.stream()).collect(Collectors.toList());
            List<String> noneMatchIdList = collect.stream().filter((e) -> Arrays.stream(routeDefinitions).noneMatch((t) -> Objects.equals(t.getId(), e))).collect(Collectors.toList());
            for (String id : noneMatchIdList) {
                try {
                    this.routeService.deleteRoute(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (RouteDefinition routeDefinition : routeDefinitions) {
                try {
                    this.routeService.updateRoute(routeDefinition);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (JsonProcessingException e) {
            log.error("Nacos配置文件格式有误，不能将配置的路由结构转为 RouteDefinition.{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
