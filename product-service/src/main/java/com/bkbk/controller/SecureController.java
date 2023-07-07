package com.bkbk.controller;


import com.bkbk.entity.Center;
import com.bkbk.entity.Substation;
import com.bkbk.service.SecureService;
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
    public ResultVo login(@RequestBody Substation substation){


        return secureService.login(substation.getName(),substation.getPassword());
    }


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResultVo register(@RequestBody Center center) {

        center.setPassword((passwordEncoder.encode(center.getPassword())));
        // 将用户实体对象添加到数据库

        return secureService.register(center);
    }

    @PostMapping("/setPassword")
    public ResultVo setPassword(@RequestBody Center center) {


        return secureService.setPassword(center.getId(),center.getPassword());
    }






}
