package com.yzb.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzb.provider.vo.Dept;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptDao extends BaseMapper<Dept> {
}
