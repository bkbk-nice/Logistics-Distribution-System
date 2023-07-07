package com.bkbk.controller;



import com.bkbk.entity.Substation;

import com.bkbk.service.Iservice.SecureService;
import com.bkbk.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("secure")
public class SecureController {

        @Autowired
        private SecureService secureService;


    @PostMapping("/login")
    public ResultVo login(@RequestBody Substation substation){


        return secureService.login(substation.getName(),substation.getPassword());
    }


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResultVo register(@RequestBody Substation substation) {

        substation.setPassword((passwordEncoder.encode(substation.getPassword())));
        // 将用户实体对象添加到数据库

        return secureService.register(substation);
    }

    @PostMapping("/setPassword")
    public ResultVo setPassword(@RequestBody Substation substation) {
        System.out.println("controller");

        return secureService.setPassword(substation.getId(),substation.getPassword());
    }






}
