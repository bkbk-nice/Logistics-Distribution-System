package com.bkbk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bkbk.entity.Client;
import com.bkbk.entity.Order;
import com.bkbk.mapper.OrderMapper;
import com.bkbk.service.OrderService;
import com.bkbk.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public ResultVo listPageByDynamics(String keyword, Integer pageNumber, Integer pageSize) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        if(keyword!=null && !keyword.isEmpty()){
           // queryWrapper.select("id","name","phone","email","identity","address","image_url","createtime","updatetime")

            queryWrapper    .like("client_id", keyword ).or().like("id",keyword);
        }
        //System.out.println("keyword:"+keyword);
        queryWrapper.orderByDesc("create_time");

        PageHelper.startPage(pageNumber,pageSize);

//        PageInfo pageInfo = new PageInfo( clientMapper.selectdynamic(keyword));
        PageInfo pageInfo = new PageInfo( orderMapper.selectList(queryWrapper));

        return  ResultVo.success(pageInfo);
    }
}
