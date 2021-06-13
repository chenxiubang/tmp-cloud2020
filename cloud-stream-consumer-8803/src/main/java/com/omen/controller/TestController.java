package com.omen.controller;

import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * CREATE BY OMEN ON 2021.06.12 22:41 星期六
 * DESC:
 */
@Component
@EnableBinding(Sink.class)//TODO 这里不要忘记指定大Sink
public class TestController {

    @Value("${server.port}")
    private String port;

    @StreamListener(Sink.INPUT) //TODO 我他妈没看到为什么就是没收到消息 ， exchange交换机上也已经有了实例
    public void input(Message<String> msg) {
        var payload = msg.getPayload(); //TODO 发送用withPayload 这里用getPayLoad
        System.out.println("端口号是：" + port + "," + "消息是：" + payload);
    }
}
