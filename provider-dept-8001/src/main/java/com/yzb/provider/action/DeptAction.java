package com.yzb.provider.action;

import com.yzb.common.dto.DeptDTO;
import com.yzb.common.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return this.iDeptService.get(id);
    }

    @GetMapping("list")
    public List<DeptDTO> list() {
        return this.iDeptService.list();
    }

    @PostMapping("add")
    public boolean add(@RequestBody DeptDTO deptDTO) {
        return this.iDeptService.add(deptDTO);
    }


}
