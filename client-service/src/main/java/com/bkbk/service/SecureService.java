package com.bkbk.service;

import com.bkbk.entity.Client;
import com.bkbk.vo.ResultVo;

public interface SecureService {

    ResultVo login(String name,String password);

    ResultVo register(Client client);
}
