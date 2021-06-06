package com.cxb.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CREATE BY OMEN ON 2021.06.06 18:59 星期日
 * DESC:
 */
@RestController
@RequestMapping("/config")
@RefreshScope//spring cloud config里面的注解 据说实现刷新功能？
public class TestController {

    @Value("${omen.cxb}")
    private String info;

    @GetMapping("/test")
    public String info() {
        return info;
    }
}
