package com.bkbk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bkbk.entity.AllocationList;
import com.bkbk.entity.TaskList;
import com.bkbk.mapper.TaskListMapper;
import com.bkbk.service.Iservice.DeliverymanService;
import com.bkbk.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class DeliverymanServiceImpl implements DeliverymanService {

    @Autowired
    private TaskListMapper taskListMapper;

    @Override
    public ResultVo getTask(Integer id,String keyword, Integer pageNumber, Integer pageSize) {
        QueryWrapper<TaskList> queryWrapper = new QueryWrapper<>();
        if(keyword!=null && !keyword.isEmpty()){
            queryWrapper.like("id", keyword ).or().like("client_phone",keyword);
        }
        queryWrapper.eq("deliveryman_id",id);

        queryWrapper.orderByDesc("create_time");

        PageHelper.startPage(pageNumber,pageSize);
        PageInfo pageInfo = new PageInfo( taskListMapper.selectList(queryWrapper));

        return  ResultVo.success(pageInfo);
    }

    @Override
    public ResultVo sureTask(Integer id, Integer taskId) {

        TaskList taskList1 = taskListMapper.selectById(taskId);

        if(taskList1==null){
            return  ResultVo.fail("无此任务单");
        }else {
            if(taskList1.getSubstationId()!=id){
                return ResultVo.fail("配送员不一致");
            }
            if(taskList1.getStatus()!=2){
                return ResultVo.fail("已签收");
            }
        }


        //更新状态
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",taskId);
        updateWrapper.set("status",3);
        Date now = new Date();
        updateWrapper.set("update_time",now);
        taskListMapper.update(null, updateWrapper);

        return ResultVo.success();
    }

}
