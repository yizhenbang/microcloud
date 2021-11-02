package com.yzb.nacos.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;

import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class NacosConfigListener {
    private static final String SERVER_ADDR = "nacos-server:8848";
    private static final String NAMESPACE = "d9ea8469-7238-4e2a-ba66-863f09a8d555";
    private static final String GROUP = "microCloudGroup";
    private static final String DATA_ID = "com.yzb.nacos.config";

    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        //如果要进行配置项的保存，这个Key就很有讲究，很严格，不能出错
        prop.put(PropertyKeyConst.SERVER_ADDR, SERVER_ADDR);//配置nacos服务器地址
        prop.put(PropertyKeyConst.NAMESPACE, NAMESPACE);//配置nacos命名空间
        //创建完连接之后，将连接包装为ConfigService
        ConfigService configService = NacosFactory.createConfigService(prop);//会抛出异常，直接向外抛出把

        // ---------------------  以下是获取配置信息  ---------------------
        // String content = configService.getConfig(DATA_ID, GROUP, 500);
        // System.err.println(content);

        configService.addListener(DATA_ID, GROUP, new Listener() {//这个Listener是SpringCloudAlibaba组件提供
            @Override
            public Executor getExecutor() {
                System.out.println("【NacosConfigListener.getExecutor】");
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("【NacosConfigListener.receiveConfigInfo】修改后的内容： = " + configInfo);
            }
        });

        //让他一直阻塞监听
        TimeUnit.MINUTES.sleep(Long.MAX_VALUE);

    }
}