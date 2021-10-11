package com.yzb.common.service;

import com.yzb.common.dto.DeptDTO;

import java.util.List;

public interface IDeptService {
    void add(DeptDTO deptDTO);

    DeptDTO get();

    List<DeptDTO> list();
}
