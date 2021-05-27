package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @SneakyThrows
    @GetMapping("/payment/test")
    public CommonResult test() {
        TimeUnit.SECONDS.sleep(5);
        return new CommonResult(200, serverPort + "处理了req");
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();  //得到所有的微服务
        for (String element : services) {
            log.info("*****element:" + element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE"); //得到一个具体微服务的所有实例
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        List<ServiceInstance> instances1 = discoveryClient.getInstances("CLOUD-ORDER-SERVER");
        instances1.forEach(x -> {
            log.info(x.getServiceId() + "\t" + x.getHost() + "\t" + x.getUri());
        });
        return this.discoveryClient;
    }

    //只传给前端CommonResult，不需要前端了解其他的组件
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据成功,serverPort：" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****插入结果：" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort：" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录,查询ID：" + id, null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "hi, i'am paymentzipkin server fall back,welcome to atguigu, O(∩_∩)O哈哈~";
    }

    @GetMapping("/payment/hystrixOK")
    public CommonResult ok() {
        return new CommonResult(200, Thread.currentThread().getName());
    }


    @HystrixCommand(fallbackMethod = "back",commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })//TODO出事的话调用这个方法，报错也走这里
    @SneakyThrows
    @GetMapping("/payment/hystrixWait")
    public CommonResult wair() {
        TimeUnit.SECONDS.sleep(3);
        return new CommonResult(200, Thread.currentThread().getName());
    }
    //这个是兜底的方法
    public CommonResult back() {
        return new CommonResult(444, "等待过久");
    }
}