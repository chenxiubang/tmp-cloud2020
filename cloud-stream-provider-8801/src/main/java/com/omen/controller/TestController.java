package com.omen.controller;

import com.omen.service.MessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * CREATE BY OMEN ON 2021.06.12 19:16 星期六
 * DESC:
 */
@RestController
public class TestController {

    @Resource
    private MessageProvider messageProvider;

    @GetMapping("/test")
    public String test() {
        messageProvider.send();
        return "ok";
    }
}
