package com.omen.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

/**
 * CREATE BY OMEN ON 2021.06.16 20:35 星期三
 * DESC:
 */
public class SentinelHandler {

    //TODO 注意要传递一个BlockException的参数

    public static CommonResult<String> method1(BlockException e) {
        return new CommonResult<>(1111, "我是兜底方法1");
    }

    public static CommonResult<String> method2(BlockException e) {
        return new CommonResult<>(2222, "我是兜底方法22222");
    }
}
