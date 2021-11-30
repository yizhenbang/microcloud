package com.yzb.sentinel.cluster;

import com.alibaba.csp.sentinel.cluster.server.ClusterTokenServer;
import com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;

public class StartClusterSentinelTokenServer {
    static {
        System.setProperty("project.name", "token-server");
        System.setProperty("csp.sentinel.log.use.pid", "true");
        System.setProperty("csp.sentinel.dashboard.server", "http://sentinel-server:8888");
        System.setProperty("csp.sentinel.api.port", "8719");
    }

    public static void main(String[] args) throws Exception {
        ClusterTokenServer tokenServer = new SentinelDefaultTokenServer();
        ClusterServerConfigManager.loadGlobalTransportConfig(new ServerTransportConfig().setIdleSeconds(6000).setPort(10086));
        tokenServer.start();
    }
}
