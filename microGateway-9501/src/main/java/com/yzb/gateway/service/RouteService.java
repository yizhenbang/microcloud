package com.yzb.gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;

/**
 * @author ZhenBang-Yi
 * @ClassName RouteService
 * @date 2022/7/20 22:13
 */
@Slf4j
@Service
public class RouteService implements ApplicationEventPublisherAware {

    @Autowired
    private RouteLocator routeLocator;

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    /**
     * 新增路由
     * @author: ZhenBang-Yi
     * @date 2022/8/2 22:10
    **/
    public boolean insertRoute(RouteDefinition routeDefinition) {
        log.info("开始执行新增路由,ID为：{}", routeDefinition.getId());
        try {
            this.routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
            this.eventPublisher.publishEvent(new RefreshRoutesEvent(this));
        } catch (Exception e) {
            log.error("路由新增失败,ID为：{}", routeDefinition.getId());
            return false;
        }
        return true;
    }

    /**
     * 删除路由
     * @author: ZhenBang-Yi
     * @date 2022/8/2 22:09
    **/
    public void deleteRoute(String id) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
            this.eventPublisher.publishEvent(new RefreshRoutesEvent(this));
            log.info("成功删除路由，路由ID为：{}",id);
        }catch (Exception e){
            log.error("删除路由失败，未找到路由，id为：{}",id);
        }
    }

    /**
     * 路由修改
     * @author: ZhenBang-Yi
     * @date 2022/8/2 22:09
    **/
    public boolean updateRoute(RouteDefinition routeDefinition) {
        try {
            this.deleteRoute(routeDefinition.getId());
            this.routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
            this.eventPublisher.publishEvent(new RefreshRoutesEvent(this));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新路由失败,ID为：{}", routeDefinition.getId());
            return false;
        }
        log.info("更新路由成功，ID为：{}",routeDefinition.getId());
        return true;
    }

    /**
     * 用户获取当前网关中所有的路由ID
     * @author: ZhenBang-Yi
     * @date 2022/8/2 22:09
    **/
    public ArrayList<List<String>> getRoutes(){
        Flux<Route> routes = this.routeLocator.getRoutes();
        Mono<List<String>> idList = routes.map((e) -> e.getId()).collectList();
        ArrayList<List<String>> strings = new ArrayList<>();
        idList.subscribe((e)->{
            log.info("读取到ID有：{}",e);
            strings.add(e);
        });
        return strings;
    }
}
