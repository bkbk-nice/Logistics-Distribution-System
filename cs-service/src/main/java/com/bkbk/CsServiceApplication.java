package com.bkbk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("com.bkbk.mapper")
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class CsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CsServiceApplication.class,args);
    }
}
