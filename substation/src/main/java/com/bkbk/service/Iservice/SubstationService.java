package com.bkbk.service.Iservice;

import com.bkbk.entity.TaskList;
import com.bkbk.vo.ResultVo;

public interface SubstationService {

    ResultVo getTask(Integer id,String  keyword, Integer pageNumber, Integer pageSize);

    ResultVo  getDeliveryman();

    ResultVo chooseDeliveryman(TaskList taskList);
}
