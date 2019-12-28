package com.cloud.gateway;

import com.cloud.gateway.bootstrap.ProxyServerBootstrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class GatewayProxyApplication {


    public static void main(String[] args) {
        // SpringApplication.run(GatewayProxyApplication.class, args);

        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(GatewayProxyApplication.class);

        springApplicationBuilder.web(WebApplicationType.NONE);

        springApplicationBuilder.run(args);

        ProxyServerBootstrap proxyServerBootstrap = new ProxyServerBootstrap();
        proxyServerBootstrap.start();
    }

}
