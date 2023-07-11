package com.bkbk.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bkbk.entity.Inventory;
import com.bkbk.entity.Order;
import com.bkbk.entity.Product;

import com.bkbk.entity.TaskList;
import com.bkbk.entity.form.OrderForm;
import com.bkbk.feign.ClientServiceFeignClient;
import com.bkbk.feign.CsServiceFeignClient;
import com.bkbk.mapper.InventoryMapper;
import com.bkbk.mapper.OrderMapper;
import com.bkbk.mapper.ProductMapper;
import com.bkbk.mapper.TaskListMapper;
import com.bkbk.service.OrderService;
import com.bkbk.vo.ResultVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;


@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderMapper orderMapper;


    @Autowired
    private ClientServiceFeignClient clientServiceFeignClient;

    @Autowired
    private CsServiceFeignClient csServiceFeignClient;

    @Autowired
    private ProductMapper productMapper;


    @Autowired
    private TaskListMapper taskListMapper;

    @Autowired
    private InventoryMapper inventoryMapper;

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
        order.setStatus(0);

        Date now =  new Date();
        order.setCreateTime(now);
        order.setUpdateTime(now);

        //查询库存
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id",order.getProductId());
         Inventory inventory = inventoryMapper.selectOne(queryWrapper);

         if(inventory.getQuantity()>=order.getNum()){
             order.setStatus(0);
             UpdateWrapper updateWrapper = new UpdateWrapper();
             updateWrapper.eq("product_id",order.getProductId());
             updateWrapper.set("quantity",inventory.getQuantity()-order.getNum());
             updateWrapper.set("assigned",inventory.getAssigned()+order.getNum());
             inventoryMapper.update(null,updateWrapper);
         }else{
             order.setStatus(1);
         }

        //客服sse
        new Thread(() -> {
            System.out.println("异步线程 =====> 开始 =====> " + System.currentTimeMillis());
            try{
                Thread.sleep(5000);
                String res = csServiceFeignClient.push("yctf");

            }catch (InterruptedException e){
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("异步线程 =====> 结束 =====> " + System.currentTimeMillis());
        }).start();

        orderMapper.insert(order);
        if(order.getStatus()==0){
            return ResultVo.success();
        }else if(order.getStatus()==1){
            return ResultVo.success("订购成功，订单为缺货状态，请耐心等待");
        }
        return ResultVo.fail();

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

        Order order = orderMapper.selectOne(queryWrapper);
        if(order ==null) {
            return ResultVo.fail("订单不存在");
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("order_id",orderId);


        TaskList taskList = taskListMapper.selectOne(queryWrapper1);

        if(taskList==null){
            return ResultVo.fail("任务单不存在");
        }

        return ResultVo.success(taskList);
    }

    @Override
    public ResultVo makeSureGet(Integer clientId, Integer orderId) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("client_id",clientId);
        queryWrapper.eq("id",orderId);

        Order order = orderMapper.selectOne(queryWrapper);
        if(order ==null) {
            return ResultVo.fail("订单不存在");
        }else{
            if(order.getStatus()!=2){
                ResultVo.fail("现在不能收货");
            }
        }
        //修改订单状态
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",orderId);
        updateWrapper.set("status",3);
        orderMapper.update(null,updateWrapper);

        //修改任务单状态和金额
        Integer taskId = order.getTaskId();
        UpdateWrapper updateWrapperForTask = new UpdateWrapper();
        updateWrapperForTask.eq("id",taskId);
        updateWrapperForTask.set("status",4);
        updateWrapperForTask.set("money",order.getPrice()*order.getNum());
        taskListMapper.update(null,updateWrapperForTask);

        return ResultVo.success();
    }

}
