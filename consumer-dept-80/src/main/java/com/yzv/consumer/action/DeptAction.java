package com.yzv.consumer.action;

import com.yzb.common.dto.DeptDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * ClassName: DeptAction
 * Description:
 * date: 2021/10/12 18:49
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/consumer/dept/*")
public class DeptAction {
    private static final String DEPT_ADD_URL = "http://provider-dept-8001:8001/provider/dept/add";
    private static final String DEPT_LIST_URL = "http://provider-dept-8001:8001/provider/dept/list";
    private static final String DEPT_GET_URL = "http://provider-dept-8001:8001/provider/dept/get/";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("add")
    public Object add(DeptDTO deptDTO) {
        return this.restTemplate.postForObject(DEPT_ADD_URL, deptDTO, Boolean.class);
    }

    @GetMapping("list")
    public Object list() {
        return this.restTemplate.getForObject(DEPT_LIST_URL, List.class);
    }

    @GetMapping("get")
    public Object get(Long id) {
        return this.restTemplate.getForObject(DEPT_GET_URL + id, DeptDTO.class);
    }
}