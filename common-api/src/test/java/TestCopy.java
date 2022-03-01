import com.yzb.common.dto.DeptDTO;
import com.yzb.common.utils.DeepBeanUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: TestCopy
 * Description:
 * date: 2021/10/11 20:54
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
public class TestCopy {
    @Test
    public void copy() {
        // List<DeptDTO> list = new ArrayList<>();
        // for (int i = 0; i < 10; i++) {
        //     DeptDTO deptDTO = new DeptDTO();
        //     deptDTO.setId(10L);
        //     deptDTO.setDeptNo("001");
        //     deptDTO.setDeptName("开发");
        //     deptDTO.setLoc("loc");
        //     list.add(deptDTO);
        // }
        // List<DeptDTO> deptDTOS = DeepBeanUtils.copyListProperties(list, DeptDTO::new);
        // deptDTOS.forEach(System.out::println);
    }
}
