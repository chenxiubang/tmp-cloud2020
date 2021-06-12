package com.omen.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CREATE BY OMEN ON 2021.06.12 17:40 星期六
 * DESC:
 */
@RestController
@RefreshScope//spring cloud config里面的注解 据说实现刷新功能？
public class TestController {
    @Value("${omen.cxb}")
    private String str;
    @GetMapping("/test")
    public String test() {
        return str;
    }
}
