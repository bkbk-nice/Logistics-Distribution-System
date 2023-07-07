package com.bkbk.service;

import com.bkbk.entity.AllocationList;
import com.bkbk.entity.TaskList;
import com.bkbk.vo.ResultVo;

public interface CenterService {

    ResultVo getAllocationList( String  keyword, Integer pageNumber, Integer pageSize);


    ResultVo allocationStart(AllocationList allocationList);
}
