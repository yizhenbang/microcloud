package com.yzb.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * ClassName: DefaultGlobalFilter
 * Description:
 * date: 2022/3/10 10:15
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Order(-500)
@Component
@Slf4j
public class DefaultGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("（PRE）【路径】：{}，请求方式：{}",exchange.getRequest().getPath(),exchange.getRequest().getMethod());
        return chain.filter(exchange).then(
                Mono.fromRunnable(()->{
                    log.info("（POST）【状态码】：{}，",exchange.getResponse().getStatusCode());
                })
        );
    }
}
