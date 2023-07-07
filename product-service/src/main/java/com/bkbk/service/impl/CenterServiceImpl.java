package com.bkbk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bkbk.entity.AllocationList;
import com.bkbk.entity.Deliveryman;
import com.bkbk.entity.TaskList;
import com.bkbk.mapper.AllocationListMapper;
import com.bkbk.service.CenterService;
import com.bkbk.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CenterServiceImpl implements CenterService {


    @Autowired
    private AllocationListMapper allocationListMapper;
    @Override
    public ResultVo getAllocationList(String keyword, Integer pageNumber, Integer pageSize) {
        QueryWrapper<AllocationList> queryWrapper = new QueryWrapper<>();
        if(keyword!=null && !keyword.isEmpty()){
            queryWrapper.like("id", keyword ).or().like("order_id",keyword);
        }
        queryWrapper.orderByDesc("create_time");
        PageHelper.startPage(pageNumber,pageSize);
        PageInfo pageInfo = new PageInfo( allocationListMapper.selectList(queryWrapper));
        return  ResultVo.success(pageInfo);
    }

    @Override
    public ResultVo allocationStart(AllocationList allocationList) {
        Integer id = allocationList.getId();

        AllocationList allocationList1 = allocationListMapper.selectById(id);

        if(allocationList1==null){
            return  ResultVo.fail("无此调度单");
        }
        else {
            if(allocationList1.getStatus()!=0){
                return ResultVo.fail("已分发");
            }
        }
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",allocationList1.getId());
        updateWrapper.set("status",1);
        Date now = new Date();
        updateWrapper.set("update_time",now);
        allocationListMapper.update(null, updateWrapper);

        return ResultVo.success();
    }
}
