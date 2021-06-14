package com.omen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * CREATE BY OMEN ON 2021.06.14 18:27 星期一
 * DESC:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class APP8401 {
    public static void main(String[] args) {
        SpringApplication.run(APP8401.class, args);
    }
}
