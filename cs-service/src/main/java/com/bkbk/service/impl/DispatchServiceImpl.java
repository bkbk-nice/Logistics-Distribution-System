package com.bkbk.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bkbk.entity.AllocationList;
import com.bkbk.entity.Order;
import com.bkbk.entity.Substation;
import com.bkbk.entity.TaskList;
import com.bkbk.mapper.AllocationListMapper;
import com.bkbk.mapper.OrderMapper;
import com.bkbk.mapper.SubstationMapper;
import com.bkbk.mapper.TaskListMapper;
import com.bkbk.service.DispatchService;
import com.bkbk.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class DispatchServiceImpl implements DispatchService {

    @Autowired
    private AllocationListMapper allocationListMapper;

    @Autowired
    private TaskListMapper taskListMapper;

    @Autowired
    private OrderMapper orderMapper;


    @Autowired
    private SubstationMapper substationMapper;
    @Override
    public ResultVo createAllocationListAndTaskList(AllocationList allocationList, Integer csId) {

        //检验是否存在订单，分站id,是否调度过
        Order order =  orderMapper.selectById(allocationList.getOrderId());

        if(order!=null){
            if(order.getStatus()==1){
                return  ResultVo.fail("缺货中");
            }
            if(order.getStatus()!=0){
                return  ResultVo.fail("订单已调度");
            }
        }else{
            return  ResultVo.fail("无此订单");
        }
        Substation substation =  substationMapper.selectById(allocationList.getSubstationId()) ;
        if(substation==null){
            return  ResultVo.fail("无此分站");
        }



        //新建派遣单
        Date now = new Date();
        allocationList.setSubstationName(substation.getName());
        allocationList.setCreateTime(now);
        allocationList.setUpdateTime(now);
        allocationList.setCsId(csId);

        allocationListMapper.insert(allocationList);
      //  System.out.println(allocationListId);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_id",order.getId());
        AllocationList allocationList1 = allocationListMapper.selectOne(queryWrapper);

        //System.out.println(1/0);

        //新建任务单
        TaskList taskList = new TaskList();
        taskList.setAllocationId(allocationList1.getId());
        taskList.setCreateTime(now);
        taskList.setUpdateTime(now);
        taskList.setOrderId(allocationList.getOrderId());
        taskList.setSubstationName(substation.getName());
        taskList.setSubstationId(allocationList.getSubstationId());
        taskListMapper.insert(taskList);

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper.eq("order_id",order.getId());
        TaskList taskList1 = taskListMapper.selectOne(queryWrapper);


        //更新订单



        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", allocationList.getOrderId());
        updateWrapper.set("substation_id",allocationList.getSubstationId());
        updateWrapper.set("substation_name",substation.getName());
        updateWrapper.set("status",2);
        updateWrapper.set("update_time",now);
        updateWrapper.set("task_id",taskList1.getId());

        orderMapper.update(null, updateWrapper);

        return ResultVo.success();
    }

    @Override
    public ResultVo getSubstation() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("name","id");
        return ResultVo.success(substationMapper.selectList(queryWrapper));
    }
}
