package com.yzb.common.hystrix;

import com.yzb.common.dto.DeptDTO;
import com.yzb.common.service.IDeptService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FeignHystrixFallbackFactory implements FallbackFactory<IDeptService> {

    @Override
    public IDeptService create(Throwable cause) {
        return new IDeptService() {
            @Override
            public boolean add(DeptDTO deptDTO) {
                return false;
            }

            @Override
            public DeptDTO get(long id) {
                return null;
            }

            @Override
            public List<DeptDTO> list() {
                return new ArrayList<DeptDTO>();
            }
        };
    }
}
