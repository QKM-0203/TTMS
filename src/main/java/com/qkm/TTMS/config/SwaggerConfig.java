package com.qkm.TTMS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {




    @Bean
    public Docket docket(){
        //获得当dev prod的环境
        Profiles profiles = Profiles.of("dev","prod");
        //判断是否和当前项目环境一致,就可以开关swagger了


        return new Docket(DocumentationType.SWAGGER_2)
               // .enable(false)   //默认swagger开启的,在生产状况应该关闭
                .groupName("戚凯萌")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qkm.TTMS.controller"))
                .build();
    }

    public ApiInfo apiInfo(){

        Contact contact = new Contact("戚凯萌", "https://github.com/QKM-0203?tab=repositories", "1563252248@qq.com");

        return new ApiInfo(
                "524TTMS的文档",
                "这是一个还没有完成的电影院",
                "v1.0",
                "https://github.com/QKM-0203?tab=repositories",
                 contact,
                "Apache2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}
