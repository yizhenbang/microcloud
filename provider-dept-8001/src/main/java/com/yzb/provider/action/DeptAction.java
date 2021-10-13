package com.yzb.provider.action;

import com.yzb.common.dto.DeptDTO;
import com.yzb.common.service.IDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

/**
 * ClassName: DeptAction
 * Description:
 * date: 2021/10/12 17:37
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
@RestController
@RequestMapping("/provider/dept/*")
public class DeptAction {

    @Autowired
    private IDeptService iDeptService;

    @GetMapping("echo")
    public String echo() {
        return "Hello";
    }

    @GetMapping("get/{id}")
    public DeptDTO selectOne(@PathVariable("id") Long id) {
        printHeaders("get");
        return this.iDeptService.get(id);
    }

    @GetMapping("list")
    public List<DeptDTO> list() {
        printHeaders("list");
        return this.iDeptService.list();
    }

    @PostMapping("add")
    public boolean add(@RequestBody DeptDTO deptDTO) {
        printHeaders("add");
        return this.iDeptService.add(deptDTO);
    }


    private void printHeaders(String pathName) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String s = headerNames.nextElement();
            log.info("【{}】{}->请求头->{}", pathName, s, request.getHeader(s));
        }
    }
}
