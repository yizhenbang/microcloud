package com.yzb.provider.action;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yzb.common.dto.DeptDTO;
import com.yzb.common.service.IDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    // @ApiOperation(httpMethod = "get", notes = "这个接口直接给我返回一个echo数据", value = "ECHO程序")
    public String echo() {
        return "Hello";
    }

    @GetMapping("get/{id}")
    // @HystrixCommand(fallbackMethod = "getRollBack")
    @HystrixCommand
    public DeptDTO selectOne(@PathVariable("id") Long id) {
        printHeaders("get");
        return this.iDeptService.get(id);
    }


    // public DeptDTO getRollBack(Long id) {
    //     DeptDTO deptDTO = new DeptDTO();
    //     deptDTO.setLoc("【数据回退】Loc");
    //     deptDTO.setDname("【数据回退】Dname");
    //     deptDTO.setDeptno("【数据回退】Deptno");
    //     deptDTO.setId(id);
    //     return deptDTO;
    // }

    @GetMapping("list")
    @HystrixCommand
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
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String s = headerNames.nextElement();
                log.info("【{}】{}->请求头->{}", pathName, s, request.getHeader(s));
            }
        } catch (Exception e) {
        }
    }
}
