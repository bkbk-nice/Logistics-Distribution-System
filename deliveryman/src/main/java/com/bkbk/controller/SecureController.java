package com.bkbk.controller;


import com.bkbk.entity.Deliveryman;
import com.bkbk.entity.Substation;
import com.bkbk.service.Iservice.SecureService;
import com.bkbk.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("secure")
public class SecureController {

        @Autowired
        private SecureService secureService;


    @PostMapping("/login")
    public ResultVo login(@RequestBody Deliveryman deliveryman){


        return secureService.login(deliveryman.getName(),deliveryman.getPassword());
    }


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResultVo register(@RequestBody Deliveryman deliveryman) {

        deliveryman.setPassword((passwordEncoder.encode(deliveryman.getPassword())));
        // 将用户实体对象添加到数据库

        return secureService.register(deliveryman);
    }

    @PostMapping("/setPassword")
    public ResultVo setPassword(@RequestBody Deliveryman deliveryman) {
      //  System.out.println("controller");

        return secureService.setPassword(deliveryman.getId(),deliveryman.getPassword());
    }






}
