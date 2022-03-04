package com.yzb.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;

/**
 * ClassName: LogGatewayFilter
 * Description: 做日志过滤工程
 * date: 2022/1/13 23:00
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */

@Component
@Slf4j
public class LogGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return ((exchange, chain) -> {
            Map<String, Object> attributes = exchange.getAttributes();
            log.info("【获取所有的Attr】:{}",attributes);
            log.info("【NAME:{}，VALUE:{}】", config.getName(), config.getValue());
            ServerHttpRequest request = exchange.getRequest().mutate().build();
            log.info("【请求方法：{}】，【请求路径：{}】", request.getMethod(), request.getPath());
            ServerWebExchange build = exchange.mutate().request(request).build();
            return chain.filter(exchange);
        });
    }
}
