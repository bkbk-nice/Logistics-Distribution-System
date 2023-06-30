package com.bkbk.controller;


import com.bkbk.entity.form.ClientPassword;
import com.bkbk.form.ClientForm;
import com.bkbk.service.ClientService;
import com.bkbk.util.JwtUtil;
import com.bkbk.vo.ResultVo;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/client")
public class ClientController {


    @Autowired
    private ClientService clientService;

    @Secured("ROLE_client")
    @PostMapping("/uploadAvatar")
    public ResultVo uploadAvatar(MultipartFile file ,@RequestHeader("Authorization") String token)  {



        Claims claims = JwtUtil.parse(token);
        if (claims != null) {

            String clientid = claims.getSubject();

            return   clientService.uploadAvatar(file,Integer.parseInt(clientid));

        }else{
            return ResultVo.fail("token错误");
        }

    }

    @Secured("ROLE_client")
    @GetMapping("/avatar")
    public ResultVo getAvatar(@RequestHeader("Authorization") String token)  {


        Claims claims = JwtUtil.parse(token);
        if (claims != null) {

            String clientid = claims.getSubject();

            return   clientService.getAvatar(Integer.parseInt(clientid));

        }else{
            return ResultVo.fail("token错误");
        }

    }

    @Secured("ROLE_client")
    @GetMapping("/clientInfo")
    public ResultVo getClientInfo(@RequestHeader("Authorization") String token)  {

        Claims claims = JwtUtil.parse(token);
        if (claims != null) {
            String clientid = claims.getSubject();
            return   clientService.getClientInfo(Integer.parseInt(clientid));
        }else{
            return ResultVo.fail("token错误");
        }
    }

    @Secured("ROLE_client")
    @PostMapping("/editClientInfo")
    public ResultVo editClientInfo(@RequestHeader("Authorization") String token, @RequestBody ClientForm clientForm)  {

        Claims claims = JwtUtil.parse(token);
        if (claims != null) {
            String clientid = claims.getSubject();
            return   clientService.editClientInfo(Integer.parseInt(clientid),clientForm);
        }else{
            return ResultVo.fail("token错误");
        }
    }


     @Secured("ROLE_client")
    @PostMapping("/updatePassword")
    public ResultVo getClientInfo(@RequestHeader("Authorization") String token,@RequestBody ClientPassword clientPassword)  {

        Claims claims = JwtUtil.parse(token);
        if (claims != null) {
            String clientid = claims.getSubject();
            return   clientService.updatePassword(Integer.parseInt(clientid),clientPassword);
        }else{
            return ResultVo.fail("token错误");
        }
    }

    @PostMapping("/up")
    public ResultVo up(Integer id,@RequestBody ClientPassword clientPassword)  {

            return   clientService.updatePassword(id,clientPassword);

    }

}
