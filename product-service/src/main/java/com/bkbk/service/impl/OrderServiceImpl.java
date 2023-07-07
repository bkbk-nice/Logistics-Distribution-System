package com.bkbk.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.bkbk.entity.Order;
import com.bkbk.entity.Product;

import com.bkbk.entity.form.OrderForm;
import com.bkbk.feign.ClientServiceFeignClient;
import com.bkbk.mapper.OrderMapper;
import com.bkbk.mapper.ProductMapper;
import com.bkbk.service.OrderService;
import com.bkbk.vo.ResultVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderMapper orderMapper;


    @Autowired
    private ClientServiceFeignClient clientServiceFeignClient;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResultVo preCreateOrder(Integer clientId, OrderForm orderForm,String token) {

        //完善订单信息
        Order order = new Order();
        //调用client-service
        ResultVo resultVo = clientServiceFeignClient.getName(token);
        if(resultVo.getStatus()!=0){
            return ResultVo.fail("服务调用失败");
        }else{
            String t = resultVo.getData().toString();
            String[] t1 = t.split(",");
            String[] t2 = t1[1].split("=");
            order.setClientName(t2[1]);
        }
        Product product =  productMapper.selectById(orderForm.getProductId());
        order.setAddress(orderForm.getAddress());
        order.setNum(orderForm.getNum());
        order.setPhone(orderForm.getPhone());
        order.setPrice(product.getPrice());
        order.setProductName(product.getName());
        order.setRemark(orderForm.getRemark());
        order.setTime(orderForm.getTime());
        order.setType(orderForm.getType());
        order.setClientId(clientId);

        return ResultVo.success(order);
    }

    @Override
    public ResultVo createOrder(Integer clientId, OrderForm orderForm,String token) {
        //完善订单信息
        Order order = new Order();
        //调用client-service
        ResultVo resultVo = clientServiceFeignClient.getName(token);
        if(resultVo.getStatus()!=0){
            return ResultVo.fail("服务调用失败");
        }else{
            String t = resultVo.getData().toString();
            String[] t1 = t.split(",");
            String[] t2 = t1[1].split("=");
            order.setClientName(t2[1]);
        }
        Product product =  productMapper.selectById(orderForm.getProductId());
        order.setProductId(orderForm.getProductId());
        order.setAddress(orderForm.getAddress());
        order.setNum(orderForm.getNum());
        order.setPhone(orderForm.getPhone());
        order.setPrice(product.getPrice());
        order.setProductName(product.getName());
        order.setRemark(orderForm.getRemark());
        order.setTime(orderForm.getTime());
        order.setType(orderForm.getType());
        order.setClientId(clientId);
        order.setMainImage(product.getMainImage());
        Date now =  new Date();
        order.setCreateTime(now);
        order.setUpdateTime(now);


        return ResultVo.success( orderMapper.insert(order));
    }

    @Override
    public ResultVo searchOrder(Integer id, String keyword,Integer pageNum,Integer pageSize) {



        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        if(keyword!=null && !keyword.isEmpty()){
            //  queryWrapper.select("id","name","phone","email","identity","address","image_url","createtime","updatetime")
         //   queryWrapper.like("product_name", keyword ) ;
            queryWrapper.eq("client_id",id).like("product_name", keyword );
        }else{
            queryWrapper.eq("client_id",id).orderByDesc("create_time");
        }

        PageHelper.startPage(pageNum,pageSize);

        PageInfo pageInfo = new PageInfo( orderMapper.selectList(queryWrapper));

        return  ResultVo.success(pageInfo);

    }

    @Override
    public ResultVo searchOrderDetail(Integer clientId, Integer orderId) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("client_id",clientId);
        queryWrapper.eq("id",orderId);

        return ResultVo.success(orderMapper.selectOne(queryWrapper));
    }

}
