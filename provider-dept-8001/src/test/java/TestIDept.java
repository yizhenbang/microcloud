import com.yzb.common.service.IDeptService;
import com.yzb.provider.SpringCloudProvider8001Starter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * ClassName: TestIDept
 * Description:
 * date: 2021/10/12 17:17
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = SpringCloudProvider8001Starter.class)
public class TestIDept {

    @Autowired
    private IDeptService iDeptService;

    @Test
    public void getOne(){
        System.out.println(iDeptService.get(1));
    }
}
