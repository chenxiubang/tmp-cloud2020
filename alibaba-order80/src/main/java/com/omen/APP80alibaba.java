package com.omen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * CREATE BY OMEN ON 2021.06.13 16:21 星期日
 * DESC:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class APP80alibaba {
    public static void main(String[] args) {
        SpringApplication.run(APP80alibaba.class, args);
    }
}
