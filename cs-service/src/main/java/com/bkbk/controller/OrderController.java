package com.bkbk.controller;


import com.bkbk.service.OrderService;
import com.bkbk.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @Secured( {"ROLE_admin" , "ROLE_cs"} )
    @GetMapping("/listPageByDynamics")
    public ResultVo listPageByDynamics(String keyword,
                                       @RequestParam(defaultValue = "1") Integer pageNumber,
                                       @RequestParam(defaultValue = "5") Integer pageSize  ){

        return orderService.listPageByDynamics(keyword,pageNumber,pageSize);
    }


}
