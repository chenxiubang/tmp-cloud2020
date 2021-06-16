package com.omen.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.springcloud.entities.CommonResult;
import com.omen.handler.SentinelHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CREATE BY OMEN ON 2021.06.14 19:35 星期一
 * DESC:
 */
@RestController
public class TestController {
    //这里用SentinelResource 来作为资源名
    @SentinelResource(value = "omen",blockHandlerClass = SentinelHandler.class,blockHandler = "method1")
    @GetMapping("/test")
    public CommonResult<String> hello(){
        System.out.println("hello world");
        return new CommonResult<>(0,"success");//兜底的方法返回要和一致
    }


    @SentinelResource(value = "omen",blockHandlerClass = SentinelHandler.class,blockHandler = "method2")
    @GetMapping("/test2")
    public CommonResult<String> hello2(){
        System.out.println("hello world");
        return new CommonResult<>(0,"success");
    }
}
