package com.cxb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * CREATE BY OMEN ON 2021.06.02 22:32 星期三
 * DESC:
 */
@EnableConfigServer//激活配置中心
@SpringBootApplication
@EnableEurekaClient
public class Application3344 {
    public static void main(String[] args) {
        SpringApplication.run(Application3344.class,args);
    }
}
