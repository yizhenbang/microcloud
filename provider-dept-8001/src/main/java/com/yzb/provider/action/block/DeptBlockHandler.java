package com.yzb.provider.action.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yzb.common.dto.DeptDTO;

import java.util.ArrayList;
import java.util.List;

public class DeptBlockHandler {
    public static List<DeptDTO> blockList(BlockException e){
        ArrayList<DeptDTO> deptDTOS = new ArrayList<>();
        return deptDTOS;
    }

    public static DeptDTO getBlock(Long id,BlockException e){
        DeptDTO deptDTO = new DeptDTO();
        deptDTO.setDeptno("【Block】"+id);
        return deptDTO;
    }
}
