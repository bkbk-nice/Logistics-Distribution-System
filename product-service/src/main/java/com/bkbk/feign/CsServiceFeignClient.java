package com.bkbk.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@FeignClient(name="cs-service")
public interface CsServiceFeignClient {

    @GetMapping("/sse/push")
    String push(@RequestParam String content) throws IOException;

}
