package com.yzb.provider.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzb.common.dto.DeptDTO;
import com.yzb.common.service.IDeptService;
import com.yzb.common.utils.DeepBeanUtils;
import com.yzb.provider.dao.DeptDao;
import com.yzb.provider.vo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: IDeptServiceImpl
 * Description:
 * date: 2021/10/12 17:08
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class IDeptServiceImpl implements IDeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public boolean add(DeptDTO deptDTO) {
        Dept dept = new Dept();
        DeepBeanUtils.copyProperties(deptDTO, dept);
        return this.deptDao.insert(dept) > 0;
    }

    @Override
    public DeptDTO get(long id) {
        DeptDTO deptDTO = new DeptDTO();
        DeepBeanUtils.copyProperties(this.deptDao.selectById(id), deptDTO);
        return deptDTO;
    }

    @Override
    public List<DeptDTO> list() {
        return DeepBeanUtils.copyListProperties(this.deptDao.selectList(null), DeptDTO::new);
    }
}
