package com.yzv.ribbon.rult;

        import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
        import com.alibaba.cloud.nacos.ribbon.NacosServer;
        import com.alibaba.nacos.api.exception.NacosException;
        import com.alibaba.nacos.api.naming.NamingService;
        import com.alibaba.nacos.api.naming.pojo.Instance;
        import com.netflix.client.config.IClientConfig;
        import com.netflix.loadbalancer.AbstractLoadBalancerRule;
        import com.netflix.loadbalancer.BaseLoadBalancer;
        import com.netflix.loadbalancer.ILoadBalancer;
        import com.netflix.loadbalancer.Server;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.factory.annotation.Autowired;

/**
 * ClassName: NacosWeightRule
 * Description: Nacos权重算法实现,不直接实现IRule因为要满足于适配器模式
 * date: 2021/11/20 9:57
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
public class NacosWeightRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;// 拿到发现服务配置
    private IClientConfig clientConfig;

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    @Override
    public Server choose(Object key) {
        BaseLoadBalancer loadBalancer = (BaseLoadBalancer) super.getLoadBalancer();
        String serverName = loadBalancer.getName();//获取服务名
        NamingService namingService = this.nacosDiscoveryProperties.namingServiceInstance();
        try {
            Instance instance = namingService.selectOneHealthyInstance(serverName, this.nacosDiscoveryProperties.getGroup());//根据服务名称和组查找健康服务
            return new NacosServer(instance);
        } catch (NacosException e) {
            log.error("获取健康实例有误，{}", e.getMessage());
        }
        return null;
    }
}
