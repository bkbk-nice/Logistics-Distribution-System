package com.bkbk.controller;


import com.bkbk.entity.Cs;
import com.bkbk.util.JwtUtil;
import com.bkbk.vo.ResultVo;
import io.jsonwebtoken.Claims;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@Controller
@RequestMapping(path = "sse")
public class SseController {

    private final static Map<String, SseEmitter> sseCache = new ConcurrentHashMap<>();


    @GetMapping(path = "subscribe", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter create(@RequestParam(name = "token", required = true)String token) throws IOException {

        Claims claims = JwtUtil.parse(token);
        String csId;
        if (claims == null) {
            return  null;
        }else{
             csId = claims.getSubject();
        }
        // 超时时间设置为5分钟，用于演示客户端自动重连
        SseEmitter sseEmitter = new SseEmitter(5_60_000L);
        // 设置前端的重试时间为1s
        // send(): 发送数据，如果传入的是一个非SseEventBuilder对象，那么传递参数会被封装到 data 中
        //sseEmitter.send(SseEmitter.event().reconnectTime(1000).data("连接成功"));
        sseCache.put(csId, sseEmitter);
        System.out.println("add " + csId);
       // System.out.println("现在有"+sseCache.size()+"客服");
        //sseEmitter.send("你好", MediaType.APPLICATION_JSON);
       // SseEmitter.SseEventBuilder data = SseEmitter.event().name("finish").id(csId).data("你好"+csId);
       // sseEmitter.send(data);
        // onTimeout(): 超时回调触发
        sseEmitter.onTimeout(() -> {
            System.out.println(csId + "超时");
            sseCache.remove(csId);
        });
        // onCompletion(): 结束之后的回调触发
        sseEmitter.onCompletion(() -> System.out.println("完成！"));
        return sseEmitter;
    }


    @ResponseBody
    @GetMapping(path = "push")
    public String push( String content) throws IOException {

        Set<Map.Entry<String,SseEmitter>> entries=sseCache.entrySet();
        System.out.println("需要push"+sseCache.size());
        for (Map.Entry entry:entries){
            SseEmitter sseEmitter = (SseEmitter) entry.getValue();
            if (sseEmitter != null) {
                sseEmitter.send(content);
            }
//            SseEmitter sseEmitter = sseCache.get(id);
//            if (sseEmitter != null) {
//                sseEmitter.send(content);
//            }
        }

        return "over";
    }

    @ResponseBody
    @GetMapping(path = "over")
    public String over(String token) {
        Claims claims = JwtUtil.parse(token);
        String csId;
        if (claims == null) {
            return  "异常";
        }else{
            csId = claims.getSubject();
        }
        SseEmitter sseEmitter = sseCache.get(csId);
        if (sseEmitter != null) {
            // complete(): 表示执行完毕，会断开连接
            sseEmitter.complete();
            sseCache.remove(csId);
            System.out.println("清除"+csId);
        }
        return "over";
    }
}