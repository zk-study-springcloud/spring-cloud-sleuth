package com.github.zk.gatewayservice.filter;

import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author zhaokai
 * @date 2019/11/2 下午3:55
 */
@Component
public class LoggerFilter implements GlobalFilter {
    @Autowired
    private Tracer tracer;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 在链路数据中添加自定义数据
        tracer.currentSpan().tag("operator", "forezp");
        return chain.filter(exchange);
    }
}