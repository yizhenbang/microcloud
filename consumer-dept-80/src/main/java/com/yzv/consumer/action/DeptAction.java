package com.yzv.consumer.action;

import com.yzb.common.dto.DeptDTO;
import com.yzb.common.service.IDeptService;
import com.yzv.consumer.util.RandomAccessUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/consumer/dept/*")
public class DeptAction {

    @Autowired
    private IDeptService deptService;

    @PostMapping("add")
    public Object add(DeptDTO deptDTO) {
        return this.deptService.add(deptDTO);
    }

    @GetMapping("list")
    public Object list() {
        return this.deptService.list();
    }

    @GetMapping("get")
    public Object get(Long id) {
        return this.deptService.get(id);
    }
}