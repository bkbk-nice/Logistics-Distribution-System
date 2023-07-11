package com.bkbk.controller;


import com.bkbk.entity.TaskList;
import com.bkbk.service.Iservice.DeliverymanService;
import com.bkbk.util.JwtUtil;
import com.bkbk.vo.ResultVo;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("deliveryman")
public class DeliverymanController {

    @Autowired
    private DeliverymanService deliverymanService;

    @Secured( {"ROLE_admin" , "ROLE_substation"} )
    @GetMapping("/listPageByDynamics")
    public ResultVo listPageByDynamics(
            @RequestHeader("Authorization") String token,
            String keyword,
                                       @RequestParam(defaultValue = "1") Integer pageNumber,
                                       @RequestParam(defaultValue = "5") Integer pageSize  ){


        Claims claims = JwtUtil.parse(token);
        if (claims == null) {
            return ResultVo.fail("token错误");
        }else{
            String  deliverymanId = claims.getSubject();

            return deliverymanService.getTask(Integer.parseInt(deliverymanId),keyword,pageNumber,pageSize);

        }

    }

    @Transactional
    @PostMapping("/sureMake")
    public ResultVo getProduct(   @RequestHeader("Authorization") String token,
                                  @RequestBody TaskList taskList ) {

        Claims claims = JwtUtil.parse(token);
        if (claims == null) {
            return ResultVo.fail("token错误");
        }else{
            String deliverymanId = claims.getSubject();
            return   deliverymanService.sureTask(Integer.parseInt(deliverymanId),taskList.getId());


        }

    }










}



