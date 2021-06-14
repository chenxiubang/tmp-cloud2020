package com.omen.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CREATE BY OMEN ON 2021.06.13 16:19 星期日
 * DESC:
 */
@RestController
public class TestController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/test")
    public String test() {
        return "成功调用：端口是："+port;
    }
}
