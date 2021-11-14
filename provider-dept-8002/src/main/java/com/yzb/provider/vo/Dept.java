package com.yzb.provider.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dept")
public class Dept {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String deptNo;
    private String deptName;
    private String loc;
}
