package com.bkbk.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        boolean token = exchange.getRequest().getHeaders().containsKey("token");
//        System.out.println("----全局过滤器token----"+token);
//        if (!token){
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            ServerHttpResponse response = exchange.getResponse();
//            return response.setComplete();
//        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}

