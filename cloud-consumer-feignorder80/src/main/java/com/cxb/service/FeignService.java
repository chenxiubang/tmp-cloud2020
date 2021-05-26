package com.cxb.service;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * openFeign自带负载均很
 */
@FeignClient(value = "cloud-provider-service")//feign去调用哪个微服务
@Component  //这个到底可不可以不加
public interface FeignService {

    /**
     * 两个微服务都有的前端控制器接口
     * @return
     */
    @GetMapping("/payment/test")
    CommonResult test();
}
