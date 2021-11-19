package com.yzb.test;

import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import com.yzv.consumer.SpringCloudConsumerStarter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = SpringCloudConsumerStarter.class)
public class TestServerListUpdate {
    @Test
    public void updateList() throws Exception{
        // 实例数据
        String instance = "10.15.23.2:8801,10.12.55.5:8806,12.12.120.5:8989";
        IClientConfig clientConfig = new DefaultClientConfigImpl();
        clientConfig.set(CommonClientConfigKey.ListOfServers,instance);

        // 服务列表
        ConfigurationBasedServerList serverList = new ConfigurationBasedServerList();
        serverList.initWithNiwsConfig(clientConfig);

        PollingServerListUpdater updater = new PollingServerListUpdater();
        updater.start(new ServerListUpdater.UpdateAction() {
            @Override
            public void doUpdate() {
                System.out.println("核心线程个数："+updater.getCoreThreads()+"、最后一次更新："+updater.getLastUpdate()+"、时间间隔："+updater.getDurationSinceLastUpdateMs());
            }
        });

        // 负载均衡
        ZoneAwareLoadBalancer zoneAwareLoadBalancer = new ZoneAwareLoadBalancer();
        zoneAwareLoadBalancer.setServerListImpl(serverList);
        zoneAwareLoadBalancer.setServerListUpdater(updater);
        zoneAwareLoadBalancer.getLoadBalancerStats().getServerStats().keySet().forEach(server -> {
            if (server.getPort() == 8989){
                zoneAwareLoadBalancer.markServerDown(server);//设置主机下线
            }
        });

        TimeUnit.MINUTES.sleep(Long.MAX_VALUE);
    }
}
