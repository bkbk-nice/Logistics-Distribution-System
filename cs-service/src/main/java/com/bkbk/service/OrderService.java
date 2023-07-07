package com.bkbk.service;

import com.bkbk.vo.ResultVo;

public interface OrderService {

    ResultVo listPageByDynamics(String  keyword, Integer pageNumber, Integer pageSize);
}
