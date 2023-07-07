package com.bkbk.service;

import com.bkbk.entity.AllocationList;
import com.bkbk.vo.ResultVo;

public interface DispatchService {

    ResultVo createAllocationListAndTaskList(AllocationList allocationList,Integer csId);


    ResultVo getSubstation();
}
