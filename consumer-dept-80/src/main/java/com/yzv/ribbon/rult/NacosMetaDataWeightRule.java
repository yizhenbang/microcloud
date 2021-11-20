package com.yzv.ribbon.rult;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.ExtendBalancer;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class NacosMetaDataWeightRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;
    private IClientConfig clientConfig;

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    @Override
    public Server choose(Object key) {
        BaseLoadBalancer loadBalancer = (BaseLoadBalancer) super.getLoadBalancer();
        String serverName = loadBalancer.getName();

        NamingService namingService = this.nacosDiscoveryProperties.namingServiceInstance();
        try {
            // 获取所有的健康服务
            List<Instance> instances = namingService.selectInstances(serverName, this.nacosDiscoveryProperties.getGroup(), true);
            // 遍历所有的服务，如果消费端所在的机房与其他节点机房一样的单独拿出来
            List<Instance> clusterInstance = instances.stream().filter(instance -> {
                return Objects.equals(instance.getMetadata().get("author"), this.nacosDiscoveryProperties.getMetadata().get("author"));
            }).collect(Collectors.toList());

            log.info("消费端一个机房的服务节点有{}个：{}", clusterInstance.size(), clusterInstance);
            // 如果此时与本消费端在同一个机房的额服务实例的话，就返回全部的服务实例
            List<Instance> chooseInstance = null;
            if (CollectionUtils.isEmpty(clusterInstance)) {
                chooseInstance = instances;
            } else {
                chooseInstance = clusterInstance;
            }
            // 然后根据权重的方式，只拿到一个服务实例
            Instance hostByRandomWeight2 = ExtendBalancer.getHostByRandomWeight2(chooseInstance);
            return new NacosServer(hostByRandomWeight2);
        } catch (NacosException e) {
            e.printStackTrace();
            return null;
        }
    }
}
