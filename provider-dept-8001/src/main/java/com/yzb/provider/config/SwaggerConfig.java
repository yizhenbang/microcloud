package com.yzb.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * ClassName: SwaggerConfig
 * Description:
 * date: 2021/10/13 14:57
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
public class SwaggerConfig {

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().license("MyLicense").licenseUrl("MyLicenseUrl")
                .contact(new Contact("易振邦", "service.yzb.com", "1628755314@qq.com"))
                .description("这个项目就是为了学习SpringCloud而打造")
                .title("标题")
                .version("1.0.0")
                .build();
    }

    @Bean
    public Docket getDocket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);//文档的版本类型
        docket.apiInfo(getApiInfo())
                .select()// 设置具体的接口包，因为需要为接口给一个完整详细说明
                .apis(RequestHandlerSelectors.basePackage("com.yzb.provider.action")).build();
        return docket;
    }
}
