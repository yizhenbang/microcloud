package com.yzv.consumer.action;

import com.yzb.common.dto.DeptDTO;
import com.yzv.consumer.util.RandomAccessUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/consumer/dept/*")
public class DeptAction {
    private static final String DEPT_ADD_URL = "/provider/dept/add";
    private static final String DEPT_LIST_URL = "/provider/dept/list";
    private static final String DEPT_GET_URL = "/provider/dept/get/";
    private static final String SERVER_NAME = "provider-dept";
    @Autowired
    private RandomAccessUtil randomAccessUtil;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("add")
    public Object add(DeptDTO deptDTO) {
        String targetUrl = randomAccessUtil.getTargetUrl(SERVER_NAME, DEPT_ADD_URL);
        return this.restTemplate.postForObject(targetUrl, deptDTO, Boolean.class);
    }

    @GetMapping("list")
    public Object list() {
        String targetUrl = randomAccessUtil.getTargetUrl(SERVER_NAME, DEPT_LIST_URL);
        return this.restTemplate.getForObject(targetUrl, List.class);
    }

    @GetMapping("get")
    public Object get(Long id) {
        String targetUrl = randomAccessUtil.getTargetUrl(SERVER_NAME, DEPT_LIST_URL);
        log.info("get:{}",targetUrl);
        return this.restTemplate.getForObject(targetUrl + id, DeptDTO.class);
    }
}