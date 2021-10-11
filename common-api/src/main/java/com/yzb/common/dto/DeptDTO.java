package com.yzb.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeptDTO implements Serializable {
    private Long id;
    private String deptNo;
    private String deptName;
    private String loc;//所属数据库
}
