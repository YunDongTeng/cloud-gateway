package com.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 开启事务
@EnableTransactionManagement
@SpringBootApplication
public class GatewayManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayManageApplication.class, args);
    }

}
