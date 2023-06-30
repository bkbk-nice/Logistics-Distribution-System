package com.bkbk.service;

import com.bkbk.form.ClientForm;
import com.bkbk.vo.ResultVo;

public interface ClientService {


    ResultVo  listPageByDynamics(String  keyword,Integer pageNumber,Integer pageSize);
    ResultVo addClient(ClientForm clientForm);

    ResultVo editClient(ClientForm clientForm);

    ResultVo delClient(Integer id);
}
