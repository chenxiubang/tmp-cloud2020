package com.cxb.com.cxb.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.cxb.service.FeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "back")//这个是hystrix的注解，放在类的脑袋上，比如这个，默认兜底方法是back
public class FeignController {
    @Resource
    private FeignService feignService;

    @GetMapping("/feignTest")
    public CommonResult test() {
        return feignService.test();
    }


    @GetMapping("/hystrix1")
    public CommonResult test2() {
        return feignService.hystrixOK();
    }


    @HystrixCommand(fallbackMethod = "bbb", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")//客户端只等待500毫秒
    })
    @GetMapping("/hystrix2")
    public CommonResult test3() {
        return feignService.hystrixWati();
    }

    public CommonResult bbb() {
        return new CommonResult(200, "客户端等半秒就不等了");
    }
}
