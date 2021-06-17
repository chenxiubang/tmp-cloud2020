package io.omen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * CREATE BY OMEN ON 2021.06.17 22:21 星期四
 * DESC:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class APP2001 {
    public static void main(String[] args) {
        SpringApplication.run(APP2001.class, args);
    }
}
