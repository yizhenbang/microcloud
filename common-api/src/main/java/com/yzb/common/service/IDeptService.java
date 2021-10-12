package com.yzb.common.service;

import com.yzb.common.dto.DeptDTO;

import java.util.List;

public interface IDeptService {
    boolean add(DeptDTO deptDTO);

    DeptDTO get(long id);

    List<DeptDTO> list();
}
