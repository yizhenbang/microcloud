package com.yzb.provider.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Component
@Slf4j
public class SentinelBlockExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        log.info("【SentinelBlockExceptionHandler】handle");
        HashMap map = new HashMap();
        map.put("URL", request.getRequestURI());
        map.put("rule", e.getRule());
        map.put("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        String message = "";
        if (e instanceof FlowException) {
            message = "流控异常";
        } else if (e instanceof ParamFlowException) {
            message = "热点参数异常";
        } else if (e instanceof AuthorityException) {
            message = "授权异常";
        } else if (e instanceof SystemBlockException) {
            message = "系统异常";
        } else if (e instanceof DegradeException) {
            message = "降级熔断异常";
        } else {
            message = "其他异常";
        }
        map.put("message", message);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getWriter(), map);
    }
}
