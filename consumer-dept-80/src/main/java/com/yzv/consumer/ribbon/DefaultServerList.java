package com.yzv.consumer.ribbon;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: DefaultServerList
 * Description: 自定义的ServerList
 * date: 2021/11/17 16:40
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
@Component
public class DefaultServerList implements ServerList<Server> {
    @Override
    public List<Server> getInitialListOfServers() {//获取初始化服务列表
        log.info("初始化服务列表~");
        return null;
    }

    @Override
    public List<Server> getUpdatedListOfServers() {//获取更新后的服务列表
        List<Server> servers = new ArrayList<>();
        servers.add(new Server("127.0.0.1", 8001));
        servers.add(new Server("127.0.0.1", 8002));
        log.info("获取更新服务列表，{}", servers);
        return servers;
    }
}
