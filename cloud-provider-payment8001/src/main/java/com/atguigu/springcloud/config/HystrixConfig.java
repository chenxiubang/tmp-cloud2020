package com.atguigu.springcloud.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import lombok.experimental.var;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixConfig {

    /**
     * 必须要把这个配置到servlet里面才能被图形化监控
     * TODO 你需要请求后接口后再看图形化监控 ，不然那边只有loading
     * TODO 不需要刷新图形化监控，他自己就是动态
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        var hystrix = new HystrixMetricsStreamServlet();
        var bean = new ServletRegistrationBean(hystrix);
        bean.setLoadOnStartup(1);
        bean.addUrlMappings("/hystrix.stream");
        bean.setName("HystrixMetricsStreamServlet");//名字不可以为空 不然报错
        return bean;
    }
}
