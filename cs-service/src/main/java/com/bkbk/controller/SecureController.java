package com.bkbk.controller;


import com.bkbk.entity.Cs;
import com.bkbk.service.SecureService;
import com.bkbk.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("secure")
public class SecureController {

        @Autowired
        private SecureService secureService;


    @PostMapping("/login")
    public ResultVo login(String name,String password){


        return secureService.login(name,password);
    }


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResultVo register(String name ,String password) {
        Cs cs = new Cs();
        // 调用加密器将前端传递过来的密码进行加密
        cs.setName(name);
        cs.setPassword((passwordEncoder.encode(password)));
        // 将用户实体对象添加到数据库

        return secureService.register(cs);
    }





}
