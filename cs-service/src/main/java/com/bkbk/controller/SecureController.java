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



//    @Autowired
//    private AuthenticationManager authenticationManager;




    @PostMapping("/login")
    public ResultVo login(String name,String password){


        // 生成一个包含账号密码的认证信息
//        Authentication token = new UsernamePasswordAuthenticationToken(csName ,csPassword);
//        // AuthenticationManager校验这个认证信息，返回一个已认证的Authentication
//
//        System.out.println("2-1");
//        Authentication authentication = authenticationManager.authenticate(token);
//        // 将返回的Authentication存到上下文中
//        System.out.println("2-2");
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);

       // return ResultVo.success();

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
