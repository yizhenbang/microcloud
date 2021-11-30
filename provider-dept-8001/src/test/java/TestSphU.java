import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestSphU {
    public static void main(String[] args) {
        // 初始化规则
        initFlowRules();
        while (true) {
            Entry entry = null;
            try {
                entry = SphU.entry("YzhenB");
                System.out.println("【你好】");
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            } finally {
                if (entry!=null){
                    entry.exit();
                }
            }
        }
    }

    public static void initFlowRules() {
        List<FlowRule> flowRules = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("YzhenB");//设置资源名
        flowRule.setCount(10);// 每秒允许10个请求
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);// 根据QPS进行限流
        flowRules.add(flowRule);

        FlowRuleManager.loadRules(flowRules);
    }
}
