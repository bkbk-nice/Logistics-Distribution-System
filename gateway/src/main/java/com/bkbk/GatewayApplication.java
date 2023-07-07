package com.bkbk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
       // System.out.println(Runtime.getRuntime().availableProcessors());
        SpringApplication.run(GatewayApplication.class,args);
    }
}
