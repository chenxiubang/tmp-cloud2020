package com.cxb.com.cxb.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.cxb.service.FeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FeignController {
    @Resource
    private FeignService feignService;

    @GetMapping("/feignTest")
    public CommonResult test() {
        return feignService.test();
    }
}
