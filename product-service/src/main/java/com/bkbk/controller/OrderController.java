package com.bkbk.controller;


import com.bkbk.entity.form.OrderForm;

import com.bkbk.service.OrderService;
import com.bkbk.util.JwtUtil;
import com.bkbk.vo.ResultVo;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

@RestController
@RequestMapping("order")
public class OrderController {


    @Autowired
    private OrderService orderService;


    @PostMapping("/preCreateOrder")
    public ResultVo preCreateOrder(@RequestHeader("Authorization") String token, @RequestBody @Valid OrderForm orderForm, BindingResult bindingResult)  {

        Claims claims = JwtUtil.parse(token);
        if (claims != null) {
            String clientid = claims.getSubject();
            if (bindingResult.getErrorCount() >0) {
                return ResultVo.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
            }
            return   orderService.preCreateOrder(Integer.parseInt(clientid),orderForm,token);
        }else{
            return ResultVo.fail("token错误");
        }
    }


    @PostMapping("/createOrder")
    public ResultVo createOrder(@RequestHeader("Authorization") String token, @RequestBody @Valid OrderForm orderForm, BindingResult bindingResult)  {

        Claims claims = JwtUtil.parse(token);
        if (claims != null) {
            String clientid = claims.getSubject();
            if (bindingResult.getErrorCount() >0) {
                return ResultVo.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
            }
            return   orderService.createOrder(Integer.parseInt(clientid),orderForm,token);
        }else{
            return ResultVo.fail("token错误");
        }
    }

    @GetMapping("/searchOrder")
    public ResultVo searchOrder(@RequestHeader("Authorization") String token,
                                String keyword,
                                Integer pageNum,
                                Integer pageSize)  {
        System.out.println("pageNum" + pageNum);
        System.out.println("pageSize" + pageSize);
        Claims claims = JwtUtil.parse(token);
        if (claims != null) {
            String clientid = claims.getSubject();
            return   orderService.searchOrder(Integer.parseInt(clientid),keyword,pageNum,pageSize);
        }else{
            return ResultVo.fail("token错误");
        }
    }



    @GetMapping("/searchOrderDetail")
    public ResultVo searchOrderDetail(@RequestHeader("Authorization") String token, Integer orderId)  {

        Claims claims = JwtUtil.parse(token);
        if (claims != null) {
            String clientid = claims.getSubject();

            return   orderService.searchOrderDetail(Integer.parseInt(clientid),orderId);
        }else{
            return ResultVo.fail("token错误");
        }
    }






}
