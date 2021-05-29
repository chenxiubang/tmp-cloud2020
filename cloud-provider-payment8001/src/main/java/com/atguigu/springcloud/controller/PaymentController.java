package com.atguigu.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import lombok.SneakyThrows;
import lombok.experimental.var;
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


    @HystrixCommand(fallbackMethod = "back", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
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


    //测试服务熔断
    @HystrixCommand(fallbackMethod ="back2",commandProperties = {
            //1 是否开启熔断器 键值如果记不住 可以用hystrixPropertiesManager 来找，注意键值都是String
            @HystrixProperty(name= HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED,value = "true"),

            //2.这个怎么说呢 只能说次数
            @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,value = "10"),

            //3这个是时间窗口期
            @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS,value = "10000"),

            //4这个是错误率
            @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,value = "50")
    })
    @GetMapping("/breakDown")
    public CommonResult<String> breakDown(@RequestParam("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("不能输入负数,您现在输入的id是："+id);
        }
        var uid = IdUtil.simpleUUID();
        return new CommonResult<>(200,"success","流水号"+uid);
    }
    //TODO 记住兜底的方法返回类型要和原来方法一样 包括泛型
    public CommonResult<String> back2(Integer id) {//这个接盘的方法和原来的方法入参一样就可以了
        return new CommonResult(444, "sorry","id不能为负数,您现在输入的id是："+id);
    }

}