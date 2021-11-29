package com.yzb.provider.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class SentinelRequestOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest request) {
        String serviceName = request.getParameter("serviceName");
        if (StringUtils.isEmpty(serviceName)){
            serviceName = request.getHeader("serviceName");
        }
        if (StringUtils.isEmpty(serviceName)){
            serviceName = request.getRemoteAddr();
        }
        log.info("ServiceName={}",serviceName);
        return serviceName;
    }
}
