package com.qkm.TTMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@EnableOpenApi
@SpringBootApplication
public class TtmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TtmsApplication.class, args);
    }

}
