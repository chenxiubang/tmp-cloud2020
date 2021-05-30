package com.cxb.config;

import lombok.experimental.var;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean //硬编码的方式
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        var routes = builder.routes();
        //TODO 记住路由转发请求原来请求路径只是换了个前面协议域名端口 后面拼接的是一样的
        var routeLocator = routes.route(
                "omen",
                r -> r.path("/guoji").uri("http://news.baidu.com")   //网关路由映射 TODO https不能路由
                //r -> r.path("/guoji").uri("http://news.baidu.com/guoji")   //这样写也是一样的
        ).build();
        return routeLocator;
    }
}
