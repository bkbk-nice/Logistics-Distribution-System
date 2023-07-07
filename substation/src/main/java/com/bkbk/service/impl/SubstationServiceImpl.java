package com.bkbk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bkbk.entity.AllocationList;
import com.bkbk.entity.Deliveryman;
import com.bkbk.entity.TaskList;
import com.bkbk.mapper.AllocationListMapper;
import com.bkbk.mapper.DeliverymanMapper;
import com.bkbk.mapper.TaskListMapper;
import com.bkbk.service.Iservice.SubstationService;
import com.bkbk.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class SubstationServiceImpl implements SubstationService {

    @Autowired
    private TaskListMapper taskListMapper;

    @Autowired
    private DeliverymanMapper deliverymanMapper;

    @Autowired
    private AllocationListMapper allocationListMapper;
    @Override
    public ResultVo getTask(Integer id,String keyword, Integer pageNumber, Integer pageSize) {
        QueryWrapper<TaskList> queryWrapper = new QueryWrapper<>();
        if(keyword!=null && !keyword.isEmpty()){
            // queryWrapper.select("id","name","phone","email","identity","address","image_url","createtime","updatetime")

            queryWrapper    .like("id", keyword ).or().like("order_id",keyword);
        }
        queryWrapper.eq("substation_id",id);
        //System.out.println("keyword:"+keyword);
        queryWrapper.orderByDesc("create_time");

        PageHelper.startPage(pageNumber,pageSize);

//        PageInfo pageInfo = new PageInfo( clientMapper.selectdynamic(keyword));
        PageInfo pageInfo = new PageInfo( taskListMapper.selectList(queryWrapper));

        return  ResultVo.success(pageInfo);
    }

    @Override
    public ResultVo getDeliveryman() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id","name","phone","address");
        return ResultVo.success(deliverymanMapper.selectList(queryWrapper));
    }

    @Override
    public ResultVo chooseDeliveryman(Integer id,TaskList taskList) {
        Integer deliverymanId = taskList.getDeliverymanId();

        Deliveryman deliveryman = deliverymanMapper.selectById(deliverymanId);
        TaskList taskList1 = taskListMapper.selectById(taskList.getId());


        if(deliveryman==null){
            return  ResultVo.fail("无此配送员");
        }
        if(taskList1==null){
            return  ResultVo.fail("无此任务单");
        }else {
            if(taskList1.getSubstationId()!=id){
                return ResultVo.fail("分站不一致");
            }
            if(taskList1.getStatus()==2){
                return ResultVo.fail("已开始配送");
            }
            if(taskList1.getStatus()==0){
                return ResultVo.fail("任务单未到货");
            }
        }
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",taskList1.getId());
        updateWrapper.set("deliveryman_id",deliverymanId);
        updateWrapper.set("deliveryman_name",deliveryman.getName());
        updateWrapper.set("deliveryman_phone",deliveryman.getPhone());
        updateWrapper.set("status",2);
        Date now = new Date();
        updateWrapper.set("update_time",now);
        taskListMapper.update(null, updateWrapper);


        return ResultVo.success();
    }

    @Override
    public ResultVo getProduct(Integer id, TaskList taskList) {



        TaskList taskList1 = taskListMapper.selectById(taskList.getId());

        if(taskList1==null){
            return  ResultVo.fail("无此任务单");
        }else {
            if(taskList1.getSubstationId()!=id){
                return ResultVo.fail("分站不一致");
            }
            if(taskList1.getStatus()!=0){
                return ResultVo.fail("已签收");
            }
        }

        //查看是否分发
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",taskList1.getAllocationId());
        AllocationList  allocationList =  allocationListMapper.selectOne(queryWrapper);
        if(allocationList==null){
            return ResultVo.fail("调度单不存在");
        }else{
            if(allocationList.getStatus()==0){
                return ResultVo.fail("货物未分发");
            }
        }

        //更新状态
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",taskList1.getId());
        updateWrapper.set("status",1);
        Date now = new Date();
        updateWrapper.set("update_time",now);
        taskListMapper.update(null, updateWrapper);

        UpdateWrapper updateWrapper1 = new UpdateWrapper<>();
        updateWrapper1.eq("id",taskList1.getAllocationId());
        updateWrapper1.set("status",2);
        updateWrapper1.set("update_time",now);
        allocationListMapper.update(null, updateWrapper1);


        return ResultVo.success();
    }
}
