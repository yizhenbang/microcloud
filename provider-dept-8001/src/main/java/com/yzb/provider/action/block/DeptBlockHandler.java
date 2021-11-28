package com.yzb.provider.action.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yzb.common.dto.DeptDTO;
import com.yzb.provider.vo.Dept;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeptBlockHandler {
    public static List<DeptDTO> blockList(BlockException e){
        ArrayList<DeptDTO> deptDTOS = new ArrayList<>();
        return deptDTOS;
    }

    public DeptDTO getBlock(long id){
        return null;
    }
}
