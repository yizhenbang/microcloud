package com.yzb.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * ClassName: DefaultTimeRoutePredicateFactory
 * Description:
 * date: 2021/12/26 19:35
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Component
@Slf4j
public class DefaultTimeSubsectionRoutePredicateFactory extends AbstractRoutePredicateFactory<TimeSubsectionConfig> {

    public DefaultTimeSubsectionRoutePredicateFactory() {
        super(TimeSubsectionConfig.class);
    }

    // 因为最终需要进行实践的判断，而我们存的是 String
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    // 处理类
    @Override
    public Predicate<ServerWebExchange> apply(TimeSubsectionConfig config) {
        String now = LocalDateTime.now().format(dateTimeFormatter);
        Set<String> section = new TimeSubsectionConfig().getSection();

        log.info("当前的时间为：{}，判断结果为：{}", now, section.contains(now));
        return serverWebExchange -> {
            return config.getSection().contains(now);
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {// 配置项
        log.info("shortcutFieldOrder()");
        return Collections.singletonList("section");
    }

    @Override
    public ShortcutType shortcutType() {// 需要明确定义分割类型
        return ShortcutType.GATHER_LIST;// 按照逗号进行分割
    }
}
