package com.bkbk.service.Iservice;


import com.bkbk.entity.Substation;
import com.bkbk.vo.ResultVo;

public interface SecureService {

    ResultVo login(String name,String password);

    ResultVo register(Substation substation);

    ResultVo setPassword(Integer id,String password);
}
