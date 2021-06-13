package com.omen.service.impl;

import com.omen.service.MessageProvider;
import lombok.experimental.var;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * CREATE BY OMEN ON 2021.06.12 19:04 星期六
 * DESC:
 */
@EnableBinding(Source.class) //这里要加上enableBinding注解 并且参数是Source 不要引入错误的包
public class MessageProviderImpl implements MessageProvider {

    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        var uuid = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(uuid).build());
        System.out.println("uuid:" + uuid);
        return null;
    }
}
