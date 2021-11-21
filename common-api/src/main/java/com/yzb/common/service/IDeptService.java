package com.yzb.common.service;

import com.yzb.common.config.FeignConfig;
import com.yzb.common.dto.DeptDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "provider-dept",configuration = FeignConfig.class)
public interface IDeptService {
    @PostMapping("/provider/dept/add")
    boolean add(DeptDTO deptDTO);

    @GetMapping("/provider/dept/get/{deptno}")
    DeptDTO get(@PathVariable("deptno") long id);

    @GetMapping("/provider/dept/list")
    List<DeptDTO> list();
}
