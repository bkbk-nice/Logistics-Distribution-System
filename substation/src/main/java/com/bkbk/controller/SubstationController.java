package com.bkbk.controller;


import com.bkbk.entity.AllocationList;
import com.bkbk.entity.TaskList;
import com.bkbk.service.Iservice.SubstationService;
import com.bkbk.util.JwtUtil;
import com.bkbk.vo.ResultVo;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("substation")
public class SubstationController {

    @Autowired
    private SubstationService substationService;

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
            String substationId = claims.getSubject();

            return substationService.getTask(Integer.parseInt(substationId),keyword,pageNumber,pageSize);

        }

    }



    @Transactional
    @PostMapping("/chooseDeliveryman")
    public ResultVo chooseDeliveryman(   @RequestHeader("Authorization") String token,
                                         @RequestBody TaskList taskList ) {

        Claims claims = JwtUtil.parse(token);
        if (claims == null) {
            return ResultVo.fail("token错误");
        }else{
            String substationId = claims.getSubject();
            return   substationService.chooseDeliveryman(Integer.parseInt(substationId),taskList);


        }

    }



    @Secured( {"ROLE_admin" , "ROLE_substation"} )
    @GetMapping("/getDeliveryman")
    public ResultVo getDeliveryman( ){

        return substationService.getDeliveryman();
    }


    @Transactional
    @PostMapping("/getProduct")
    public ResultVo getProduct(   @RequestHeader("Authorization") String token,
                                         @RequestBody TaskList taskList ) {

        Claims claims = JwtUtil.parse(token);
        if (claims == null) {
            return ResultVo.fail("token错误");
        }else{
            String substationId = claims.getSubject();
            return   substationService.getProduct(Integer.parseInt(substationId),taskList);


        }

    }



}
