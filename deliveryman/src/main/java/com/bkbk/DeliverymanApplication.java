package com.bkbk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.bkbk.mapper")
public class DeliverymanApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeliverymanApplication.class,args);
    }
}
