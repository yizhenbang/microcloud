package com.yzb.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * ClassName: GlobalFilters
 * Description:
 * date: 2022/3/10 10:28
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Component
@Slf4j
public class GlobalFilters {
    @Bean("firstGlobalFilter")
    @Order(500)
    public GlobalFilter firstGlobalFilter(){
        return ((exchange, chain) -> {
            log.info("（pre）");
            return Mono.fromRunnable(()->{
                log.info("（POST）");
            });
        });
    }
}
