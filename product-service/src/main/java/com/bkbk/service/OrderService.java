package com.bkbk.service;

import com.bkbk.entity.Order;
import com.bkbk.entity.form.OrderForm;
import com.bkbk.vo.ResultVo;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderService {




    ResultVo preCreateOrder(Integer clientId , OrderForm orderForm,String token);
    ResultVo createOrder(Integer clientId , OrderForm orderForm,String token);


    //clientId => order
    ResultVo searchOrder(Integer id, String keyword,Integer pageNum,Integer pageSize);

    //orderId =>detail
    ResultVo searchOrderDetail(Integer clientId,Integer orderId);
}
