package com.cxb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author omen
 * @create 2021-05-10-21:13
 */
@RestController
@Slf4j
public class MyController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/zk")
    public String method() {
        return serverPort+ "\t" +"zookeeper"+ UUID.randomUUID();
    }
}
