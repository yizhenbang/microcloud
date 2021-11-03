package com.yzb.nacos;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class NacosInstanceListener {
    private static final String SERVER_ADDR = "nacos-server:8848";
    private static final String NAME_SPACE = "d9ea8469-7238-4e2a-ba66-863f09a8d555";
    private static final String GROUP = "InstanceGroup";
    private static final String INSTANCE_ID = "com.yzb.nacos.instance";

    public static void main(String[] args) throws Exception{
        Properties prop = new Properties();
        prop.put(PropertyKeyConst.SERVER_ADDR,SERVER_ADDR);
        prop.put(PropertyKeyConst.NAMESPACE,NAME_SPACE);
        //此时要发布的不是配置项，而是服务项，创建命名服务项
        NamingService namingService = NamingFactory.createNamingService(prop);

        namingService.subscribe(INSTANCE_ID,GROUP, new EventListener() {
            @Override
            public void onEvent(Event event) {
                try {
                    System.err.println("===================================== 获取所有的数据信息 ================================");
                    namingService.getAllInstances(INSTANCE_ID,GROUP).forEach(System.err::println);
                } catch (NacosException e) {
                    e.printStackTrace();
                }
            }
        });


        //注册完之后就会持续向Nacos发送心跳
        TimeUnit.MINUTES.sleep(Long.MAX_VALUE);
    }

}
