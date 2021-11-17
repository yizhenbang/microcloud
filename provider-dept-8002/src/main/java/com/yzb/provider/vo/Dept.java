package com.yzb.provider.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dept")
public class Dept {
    @TableId(type = IdType.AUTO)
    private String deptno;
    private String dname;
    private String loc;
}
