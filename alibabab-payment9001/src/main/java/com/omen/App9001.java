package com.omen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * CREATE BY OMEN ON 2021.06.13 16:00 星期日
 * DESC:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class App9001 {
    public static void main(String[] args) {
        SpringApplication.run(App9001.class, args);
    }
}
