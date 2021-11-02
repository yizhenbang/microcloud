package com.yzb.nacos.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;

import java.util.Properties;

public class PublishNacosConfig {
    private static final String SERVER_ADDR = "nacos-server:8848";
    private static final String NAMESPACE = "d9ea8469-7238-4e2a-ba66-863f09a8d555";
    private static final String GROUP = "microCloudGroup";
    private static final String DATA_ID = "com.yzb.nacos.config";

    public static void main(String[] args) throws Exception{
        String content = "保存一点内容啊，挺好的，也很爱徐炫";
        Properties prop = new Properties();
        //如果要进行配置项的保存，这个Key就很有讲究，很严格，不能出错
        prop.put(PropertyKeyConst.SERVER_ADDR,SERVER_ADDR);//配置nacos服务器地址
        prop.put(PropertyKeyConst.NAMESPACE,NAMESPACE);//配置nacos命名空间
        //创建完连接之后，将连接包装为ConfigService
        ConfigService configService = NacosFactory.createConfigService(prop);//会抛出异常，直接向外抛出把
        boolean isOk = configService.publishConfig(DATA_ID, GROUP, content);
        System.out.println(isOk?"Nacos配置信息发布成功":"Nacos配置信息发布失败");
    }
}