package com.bkbk.service;

import com.bkbk.entity.Cs;
import com.bkbk.vo.ResultVo;

public interface SecureService {

    ResultVo login(String csName,String csPassword);

    ResultVo register(Cs cs);
}
