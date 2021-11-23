package com.yzb.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeptDTO implements Serializable {
    private String deptno;
    private String dname;
    private String loc;//所属数据库
    private Long id;
}
