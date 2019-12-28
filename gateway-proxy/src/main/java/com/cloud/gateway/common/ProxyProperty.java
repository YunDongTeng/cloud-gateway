package com.cloud.gateway.common;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "proxy")
public class ProxyProperty {

    private Integer port = 8090;

    // 默认2个接收请求线程
    private Integer bossThreads = Runtime.getRuntime().availableProcessors();

    // 工作线程数默认和cpu核数相同
    private Integer workThreads = Runtime.getRuntime().availableProcessors();

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getBossThreads() {
        return bossThreads;
    }

    public void setBossThreads(Integer bossThreads) {
        this.bossThreads = bossThreads;
    }

    public Integer getWorkThreads() {
        return workThreads;
    }

    public void setWorkThreads(Integer workThreads) {
        this.workThreads = workThreads;
    }
}
