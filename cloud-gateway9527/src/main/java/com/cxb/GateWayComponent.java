package com.cxb;

import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * CREATE BY OMEN ON 2021.06.02 21:52 星期三
 * DESC:
 */
@Slf4j
@Component
public class GateWayComponent implements Ordered, GlobalFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("现在是gateway的全局自定义过滤器");
        var request = exchange.getRequest();//获取request对象
        var username = request.getQueryParams().getFirst("username");
        if (!Objects.equals("omen",username)) {
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);//406
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 加载过滤器的顺序 越小越前面
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
