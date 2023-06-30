package com.bkbk.service;

import com.bkbk.entity.form.ClientPassword;
import com.bkbk.form.ClientForm;
import com.bkbk.vo.ResultVo;
import org.springframework.web.multipart.MultipartFile;

public interface ClientService {


    ResultVo uploadAvatar(MultipartFile file, Integer id  )  ;


     ResultVo getAvatar(Integer id);

     ResultVo updatePassword(Integer id, ClientPassword clientPassword);

     ResultVo editClientInfo(Integer id, ClientForm clientForm);

     ResultVo getClientInfo(Integer id);

}
