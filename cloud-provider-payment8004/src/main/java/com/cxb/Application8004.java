package com.cxb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author omen
 * @create 2021-05-10-21:10
 */
@SpringBootApplication
@EnableDiscoveryClient//using zookeeper
public class Application8004 {
    public static void main(String[] args) {
        SpringApplication.run(Application8004.class,args);
    }
}
