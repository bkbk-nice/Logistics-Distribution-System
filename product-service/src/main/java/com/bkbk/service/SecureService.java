package com.bkbk.service;


import com.bkbk.entity.Center;
import com.bkbk.vo.ResultVo;

public interface SecureService {

    ResultVo login(String name,String password);

    ResultVo register(Center center);

    ResultVo setPassword(Integer id,String password);
}
