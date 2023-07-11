package com.bkbk.service.Iservice;

import com.bkbk.entity.TaskList;
import com.bkbk.vo.ResultVo;

public interface DeliverymanService {

    ResultVo getTask(Integer id,String  keyword, Integer pageNumber, Integer pageSize);

    ResultVo  sureTask(Integer id,Integer taskId);





}
