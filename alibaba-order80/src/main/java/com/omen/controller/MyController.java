package com.omen.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * CREATE BY OMEN ON 2021.06.13 16:26 星期日
 * DESC:
 */
@RestController
@RefreshScope
public class MyController {
    @Resource
    private RestTemplate restTemplate;
    private static final String name = "http://nacos-payment-provider";

    @Value("${omen.nacos}")
    private String omen;

    @GetMapping("/test")
    public String test() {
        return restTemplate.getForObject(name+"/test",String.class);
    }

    @GetMapping("/testyml")
    public String test2() {
        return omen;
    }
}
