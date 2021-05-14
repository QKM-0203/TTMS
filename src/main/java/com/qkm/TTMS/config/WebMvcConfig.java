package com.qkm.TTMS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//@EnableSwagger2
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 不用经过Service操作直接返回试图.
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry){

    }

    /**
     * 将自己设置的localeResolve添加到Spring容器里
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolve();
    }


    /**
     * 设置权限,上级具备下级的所有权限
     * @return
     */
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_admin > ROLE_sell > ROLE_user");
        return hierarchy;
    }






}
