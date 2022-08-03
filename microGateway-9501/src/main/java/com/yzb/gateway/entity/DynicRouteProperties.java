package com.yzb.gateway.entity;

import com.alibaba.nacos.api.PropertyKeyConst;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author ZhenBang-Yi
 * @ClassName DynicRouteProperties
 * @date 2022/7/31 20:05
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.cloud.nacos.discovery")
public class DynicRouteProperties {
    private String dataId;
    private String namespace;
    private String clusterName;
    private String group;
    private String serverAddr;
    private String service;
    private String userName;
    private String password;
    private String dateId;
    private long timeOut;

    public Properties getProperties() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, getServerAddr());
        properties.put(PropertyKeyConst.NAMESPACE, getNamespace());
        properties.put(PropertyKeyConst.USERNAME, getUserName());
        properties.put(PropertyKeyConst.PASSWORD, getPassword());
        properties.put(PropertyKeyConst.CLUSTER_NAME, getPassword());
        return properties;
    }

}
