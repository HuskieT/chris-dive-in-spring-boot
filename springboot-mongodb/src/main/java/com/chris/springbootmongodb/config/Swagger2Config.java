package com.chris.springbootmongodb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @Auther: chris
 * @Date: 2019/4/6 11:47
 * @Description:
 */
@Configuration
@EnableSwagger2
@PropertySource("classpath:my.properties")
public class Swagger2Config {
    private static final String BASE_PACKAGE = "com.chris.springbootmongodb.controller";

    //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;


    //配置swagger2的一些基本的内容，比如扫描的包等等
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled)
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring-Boot Swagger2 构建RESTful API")
                //创建人
                .contact(new Contact("Ricky Fung", "https://github.com/TFdream", "ricky_feng@163.com"))
                //版本号
                .version("1.0.0")
                //描述
                .description("API接口描述")
                .build();
    }
}
