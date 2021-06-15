package com.omen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CREATE BY OMEN ON 2021.06.14 19:35 星期一
 * DESC:
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String hello(){
        System.out.println("hello world");
        return "hello world";
    }
}
