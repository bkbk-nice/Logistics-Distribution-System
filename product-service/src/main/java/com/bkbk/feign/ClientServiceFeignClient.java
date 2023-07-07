package com.bkbk.feign;


import com.bkbk.vo.ResultVo;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="client-service")
public interface ClientServiceFeignClient {
    @GetMapping("/client/avatar")
//    @Headers({"Content-Type: application/json;charset=UTF-8","Authorization:{token}"})
//    ResultVo getName(@Param("token") String token);

    ResultVo getName(  @RequestHeader("Authorization") String token);
}
